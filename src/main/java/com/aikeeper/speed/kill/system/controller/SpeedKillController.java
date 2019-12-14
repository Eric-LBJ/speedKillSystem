package com.aikeeper.speed.kill.system.controller;

import com.aikeeper.speed.kill.system.api.*;
import com.aikeeper.speed.kill.system.comm.Constans;
import com.aikeeper.speed.kill.system.comm.keyclass.impl.child.GoodsKey;
import com.aikeeper.speed.kill.system.comm.keyclass.impl.child.SpeedKillKey;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillUserDTO;
import com.aikeeper.speed.kill.system.domain.info.SpeedKillMessage;
import com.aikeeper.speed.kill.system.domain.vo.*;
import com.aikeeper.speed.kill.system.mq.MQProvider;
import com.aikeeper.speed.kill.system.result.CodeMessage;
import com.aikeeper.speed.kill.system.result.Result;
import com.aikeeper.speed.kill.system.utils.IdGeneratorUtils;
import com.aikeeper.speed.kill.system.utils.Md5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 19:51
 * @Version V1.0
 **/
@Controller
@RequestMapping("/speed/kill")
public class SpeedKillController implements InitializingBean {

    @Resource
    private GoodsInfoService goodsInfoService;

    @Resource
    private SpeedKillGoodsInfoService speedKillGoodsInfoService;

    @Resource
    private SpeedKillOrderInfoService speedKillOrderInfoService;

    @Resource
    private SpeedKillService speedKillService;

    @Resource
    private RedisService redisService;

    @Resource
    private MQProvider mqProvider;

    private final ConcurrentMap<Long, Boolean> cacheMap = new ConcurrentHashMap<>();

    /**
     * 系统初始化时将秒杀商品库存放到redis
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<SpeedKillGoodsInfoVO> speedKillGoodsInfoVOList = speedKillGoodsInfoService.selectAll();
        if (!ObjectUtils.isEmpty(speedKillGoodsInfoVOList)) {
            speedKillGoodsInfoVOList.forEach(item -> {
                redisService.set(GoodsKey.speedGoodsStockKey, "" + item.getGoodsId(), item.getStockCount());
                cacheMap.putIfAbsent(item.getGoodsId(), Boolean.FALSE);
            });
        }
    }

    @RequestMapping("/do_speed_kill")
    public String doSpeedKill(@RequestParam("goodsId") Long goodsId, Model model, SpeedKillUserDTO speedKillUserDTO) {

        if (ObjectUtils.isEmpty(speedKillUserDTO)) {
            return "login";
        }
        model.addAttribute("user", speedKillUserDTO);
        /**
         * 判断库存
         */
        SpeedKillGoodsInfoVO speedKillGoodsInfoVO = speedKillGoodsInfoService.selectByPrimaryKey(goodsId);
        if (ObjectUtils.isEmpty(speedKillGoodsInfoVO) || speedKillGoodsInfoVO.getStockCount() <= 0) {
            model.addAttribute("errorMessage", CodeMessage.LACK_OF_STOCK);
            return "speed_kill_failure";
        }
        /**
         * 判断是否已经秒杀到了，每个用户id只能秒杀一次
         */
        SpeedKillOrderInfoVO speedKillOrderInfoVO = speedKillOrderInfoService.getSpeedKillOrderInfoByUserAndGoodsId(speedKillUserDTO.getId(), goodsId);
        if (!ObjectUtils.isEmpty(speedKillOrderInfoVO) && !ObjectUtils.isEmpty(speedKillOrderInfoVO.getId())) {
            model.addAttribute("errorMessage", CodeMessage.CAN_NOT_REPEAT_SPEED_KILL);
            return "speed_kill_failure";
        }

        /**
         * 秒杀操作：1、减库存 2、向订单表新增一条订单数据 3、向秒杀订单表新增一条秒杀订单数据
         */
        OrderInfoVO orderInfoVO = speedKillService.speedKillGoods(dtoToVo(speedKillUserDTO), goodsInfoService.selectByPrimaryKey(goodsId));
        model.addAttribute("orderInfo", orderInfoVO);
        model.addAttribute("goods", goodsInfoService.selectByPrimaryKey(goodsId));
        return "order_detail";
    }

    @RequestMapping(value = "/kill", method = RequestMethod.POST)
    @ResponseBody
    public Result<OrderDetailVo> cacheKill(@RequestParam("goodsId") Long goodsId, SpeedKillUserDTO speedKillUserDTO) {

        if (ObjectUtils.isEmpty(speedKillUserDTO)) {
            return Result.error(CodeMessage.SESSION_ERROR);
        }
        /**
         * 判断库存
         */
        SpeedKillGoodsInfoVO speedKillGoodsInfoVO = speedKillGoodsInfoService.selectByPrimaryKey(goodsId);
        if (ObjectUtils.isEmpty(speedKillGoodsInfoVO) || speedKillGoodsInfoVO.getStockCount() <= 0) {
            return Result.error(CodeMessage.LACK_OF_STOCK);
        }
        /**
         * 判断是否已经秒杀到了，每个用户id只能秒杀一次
         */
        SpeedKillOrderInfoVO speedKillOrderInfoVO = speedKillOrderInfoService.getSpeedKillOrderInfoByUserAndGoodsId(speedKillUserDTO.getId(), goodsId);
        if (!ObjectUtils.isEmpty(speedKillOrderInfoVO) && !ObjectUtils.isEmpty(speedKillOrderInfoVO.getId())) {
            return Result.error(CodeMessage.CAN_NOT_REPEAT_SPEED_KILL);
        }

        /**
         * 秒杀操作：1、减库存 2、向订单表新增一条订单数据 3、向秒杀订单表新增一条秒杀订单数据
         */
        OrderInfoVO orderInfoVO = speedKillService.speedKillGoods(dtoToVo(speedKillUserDTO), goodsInfoService.selectByPrimaryKey(goodsId));
        OrderDetailVo vo = new OrderDetailVo();
        vo.setGoodsInfoVO(goodsInfoService.selectByPrimaryKey(goodsId));
        vo.setOrderInfoVO(orderInfoVO);
        return Result.success(vo);
    }

    @RequestMapping(value = "/{path}/mqKill", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> mqKill(@PathVariable("path") String path, @RequestParam("goodsId") Long goodsId, SpeedKillUserDTO speedKillUserDTO) {

        if (ObjectUtils.isEmpty(speedKillUserDTO)) {
            return Result.error(CodeMessage.SESSION_ERROR);
        }

        /**
         * 验证path
         */
        if (!checkPath(speedKillUserDTO.getId(), goodsId, path)) {
            return Result.error(CodeMessage.REQUEST_ILLEGAL);
        }

        /**
         * 判断库存 ,使用本地缓存，减少对redis的访问
         */
        if (cacheMap.getOrDefault(goodsId, Boolean.FALSE)) {
            return Result.error(CodeMessage.LACK_OF_STOCK);
        }
        Long currentStock = redisService.decr(GoodsKey.speedGoodsStockKey, "" + goodsId);
        if (currentStock == 0L) {
            cacheMap.put(goodsId, Boolean.TRUE);
        }

        /**
         * 判断是否已经秒杀到了，每个用户id只能秒杀一次
         */
        SpeedKillOrderInfoVO speedKillOrderInfoVO = speedKillOrderInfoService.getSpeedKillOrderInfoByUserAndGoodsId(speedKillUserDTO.getId(), goodsId);
        if (!ObjectUtils.isEmpty(speedKillOrderInfoVO) && !ObjectUtils.isEmpty(speedKillOrderInfoVO.getId())) {
            return Result.error(CodeMessage.CAN_NOT_REPEAT_SPEED_KILL);
        }

        /**
         * 入队
         */
        SpeedKillMessage speedKillMessage = new SpeedKillMessage();
        speedKillMessage.setSpeedKillUserDTO(speedKillUserDTO);
        speedKillMessage.setGoodsId(goodsId);
        mqProvider.sendSpeedKillMessage(speedKillMessage);

        return Result.success(Constans.RESPONSE_DATA_ZERO);
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    @ResponseBody
    public Result<Long> result(Model model, @RequestParam("goodsId") Long goodsId, SpeedKillUserDTO speedKillUserDTO) {
        model.addAttribute("user", speedKillUserDTO);
        if (ObjectUtils.isEmpty(speedKillUserDTO)) {
            return Result.error(CodeMessage.SESSION_ERROR);
        }
        Long result = speedKillService.getSpeedKillResult(speedKillUserDTO.getId(), goodsId);
        return Result.success(result);
    }

    @RequestMapping(value = "/path", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> getRequestPath(Model model, @RequestParam("goodsId") Long goodsId, SpeedKillUserDTO speedKillUserDTO) {
        model.addAttribute("user", speedKillUserDTO);
        if (ObjectUtils.isEmpty(speedKillUserDTO)) {
            return Result.error(CodeMessage.SESSION_ERROR);
        }
        String res = Md5Utils.md5(IdGeneratorUtils.simpleUUID());
        redisService.set(SpeedKillKey.requestPathKey, speedKillUserDTO.getId() + "_" + goodsId, res);
        return Result.success(res);
    }

    private static SpeedKillUserVO dtoToVo(SpeedKillUserDTO speedKillUserDTO) {
        SpeedKillUserVO speedKillUserVO = new SpeedKillUserVO();
        if (!ObjectUtils.isEmpty(speedKillUserDTO)) {
            BeanUtils.copyProperties(speedKillUserDTO, speedKillUserVO);
        }
        return speedKillUserVO;
    }

    private boolean checkPath(Long userId, Long goodsId, String path) {
        if (ObjectUtils.isEmpty(userId) || ObjectUtils.isEmpty(goodsId) || StringUtils.isEmpty(path)) {
            return Boolean.FALSE;
        }
        return path.equals(redisService.get(SpeedKillKey.requestPathKey, userId + "_" + goodsId, String.class));
    }
}
