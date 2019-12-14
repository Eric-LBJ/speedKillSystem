package com.aikeeper.speed.kill.system.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import com.aikeeper.speed.kill.system.annotation.AccessLimit;
import com.aikeeper.speed.kill.system.api.*;
import com.aikeeper.speed.kill.system.comm.Constans;
import com.aikeeper.speed.kill.system.comm.SpeedKillSupport;
import com.aikeeper.speed.kill.system.comm.keyclass.impl.child.GoodsKey;
import com.aikeeper.speed.kill.system.comm.keyclass.impl.child.SpeedKillKey;
import com.aikeeper.speed.kill.system.domain.info.SpeedKillMessage;
import com.aikeeper.speed.kill.system.domain.vo.*;
import com.aikeeper.speed.kill.system.mq.MQProvider;
import com.aikeeper.speed.kill.system.result.CodeMessage;
import com.aikeeper.speed.kill.system.result.Result;
import com.aikeeper.speed.kill.system.utils.IdGeneratorUtils;
import com.aikeeper.speed.kill.system.utils.Md5Utils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class SpeedKillController extends SpeedKillSupport implements InitializingBean {

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
    public String doSpeedKill(@RequestParam("goodsId") Long goodsId, Model model, SpeedKillUserVO speedKillUserVO) {

        if (ObjectUtils.isEmpty(speedKillUserVO)) {
            return "login";
        }
        model.addAttribute("user", speedKillUserVO);
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
        SpeedKillOrderInfoVO speedKillOrderInfoVO = speedKillOrderInfoService.getSpeedKillOrderInfoByUserAndGoodsId(speedKillUserVO.getId(), goodsId);
        if (!ObjectUtils.isEmpty(speedKillOrderInfoVO) && !ObjectUtils.isEmpty(speedKillOrderInfoVO.getId())) {
            model.addAttribute("errorMessage", CodeMessage.CAN_NOT_REPEAT_SPEED_KILL);
            return "speed_kill_failure";
        }

        /**
         * 秒杀操作：1、减库存 2、向订单表新增一条订单数据 3、向秒杀订单表新增一条秒杀订单数据
         */
        OrderInfoVO orderInfoVO = speedKillService.speedKillGoods(speedKillUserVO, goodsInfoService.selectByPrimaryKey(goodsId));
        model.addAttribute("orderInfo", orderInfoVO);
        model.addAttribute("goods", goodsInfoService.selectByPrimaryKey(goodsId));
        return "order_detail";
    }

    @ResponseBody
    @RequestMapping(value = "/kill", method = RequestMethod.POST)
    public Result<OrderDetailVo> cacheKill(@RequestParam("goodsId") Long goodsId, SpeedKillUserVO speedKillUserVO) {
        super.checkUser(speedKillUserVO);

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
        SpeedKillOrderInfoVO speedKillOrderInfoVO = speedKillOrderInfoService.getSpeedKillOrderInfoByUserAndGoodsId(speedKillUserVO.getId(), goodsId);
        if (!ObjectUtils.isEmpty(speedKillOrderInfoVO) && !ObjectUtils.isEmpty(speedKillOrderInfoVO.getId())) {
            return Result.error(CodeMessage.CAN_NOT_REPEAT_SPEED_KILL);
        }

        /**
         * 秒杀操作：1、减库存 2、向订单表新增一条订单数据 3、向秒杀订单表新增一条秒杀订单数据
         */
        OrderInfoVO orderInfoVO = speedKillService.speedKillGoods(speedKillUserVO, goodsInfoService.selectByPrimaryKey(goodsId));
        OrderDetailVo vo = new OrderDetailVo();
        vo.setGoodsInfoVO(goodsInfoService.selectByPrimaryKey(goodsId));
        vo.setOrderInfoVO(orderInfoVO);
        return Result.success(vo);
    }

    @ResponseBody
    @RequestMapping(value = "/{path}/mqKill", method = RequestMethod.POST)
    public Result<Integer> mqKill(@PathVariable("path") String path,
                                  @RequestParam("goodsId") Long goodsId,
                                  @RequestParam(value = "verifyCode", defaultValue = "0") String verifyCode,
                                  SpeedKillUserVO speedKillUserVO) {
        super.checkUser(speedKillUserVO);

        /**
         * 校验验证码
         */
        if (!checkVerifyCode(speedKillUserVO.getId(), goodsId, verifyCode)) {
            return Result.error(CodeMessage.VERIFY_CODE_CHECK_FAILURE);
        }

        /**
         * 校验path
         */
        if (!checkPath(speedKillUserVO.getId(), goodsId, path)) {
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
        SpeedKillOrderInfoVO speedKillOrderInfoVO = speedKillOrderInfoService.getSpeedKillOrderInfoByUserAndGoodsId(speedKillUserVO.getId(), goodsId);
        if (!ObjectUtils.isEmpty(speedKillOrderInfoVO) && !ObjectUtils.isEmpty(speedKillOrderInfoVO.getId())) {
            return Result.error(CodeMessage.CAN_NOT_REPEAT_SPEED_KILL);
        }

        /**
         * 入队
         */
        SpeedKillMessage speedKillMessage = new SpeedKillMessage();
        speedKillMessage.setSpeedKillUserVO(speedKillUserVO);
        speedKillMessage.setGoodsId(goodsId);
        mqProvider.sendSpeedKillMessage(speedKillMessage);

        return Result.success(Constans.RESPONSE_DATA_ZERO);
    }

    @ResponseBody
    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public Result<Long> result(@RequestParam("goodsId") Long goodsId, SpeedKillUserVO speedKillUserVO) {
        super.checkUser(speedKillUserVO);
        Long result = speedKillService.getSpeedKillResult(speedKillUserVO.getId(), goodsId);
        return Result.success(result);
    }

    @ResponseBody
    @AccessLimit(seconds = 5, maxCount = 5)
    @RequestMapping(value = "/path", method = RequestMethod.GET)
    public Result<String> getRequestPath(@RequestParam("goodsId") Long goodsId, SpeedKillUserVO speedKillUserVO) {
        super.checkUser(speedKillUserVO);
        String res = Md5Utils.md5(IdGeneratorUtils.simpleUUID());
        redisService.set(SpeedKillKey.requestPathKey, speedKillUserVO.getId() + "_" + goodsId, res);
        return Result.success(res);
    }

    @ResponseBody
    @RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
    public Result<String> getVerifyCodeImage(HttpServletResponse response, @RequestParam("goodsId") Long goodsId, SpeedKillUserVO speedKillUserVO) {
        super.checkUser(speedKillUserVO);
        try {
            /**
             * 使用hutool生成图形验证码，并写入输出流中
             * 自定义算数验证码
             */
            ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(100, 32, 4, 2);
            captcha.setGenerator(new MathGenerator());
            /**LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(80, 32);*/
            captcha.write(response.getOutputStream());

            /**
             * 将验证码存到redis，用于之后校验
             * ScriptEngine在jdk1.6中出现，eval方法是计算表达式的值
             */
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            Integer value = (Integer) engine.eval(captcha.getCode().replace("=", ""));
            redisService.set(SpeedKillKey.verifyCodeKey, speedKillUserVO.getId() + "_" + goodsId, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean checkPath(Long userId, Long goodsId, String path) {
        if (ObjectUtils.isEmpty(userId) || ObjectUtils.isEmpty(goodsId) || StringUtils.isEmpty(path)) {
            return Boolean.FALSE;
        }
        return path.equals(redisService.get(SpeedKillKey.requestPathKey, userId + "_" + goodsId, String.class));
    }

    private boolean checkVerifyCode(Long userId, Long goodsId, String verifyCode) {
        try {
            if (ObjectUtils.isEmpty(userId) || ObjectUtils.isEmpty(goodsId) || StringUtils.isEmpty(verifyCode)) {
                return Boolean.FALSE;
            }
            return verifyCode.equals(redisService.get(SpeedKillKey.verifyCodeKey, userId + "_" + goodsId, String.class));
        } finally {
            redisService.delete(SpeedKillKey.verifyCodeKey, userId + "_" + goodsId);
        }
    }
}
