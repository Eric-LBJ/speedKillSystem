package com.aikeeper.speed.kill.system.controller;

import com.aikeeper.speed.kill.system.api.GoodsInfoService;
import com.aikeeper.speed.kill.system.api.SpeedKillGoodsInfoService;
import com.aikeeper.speed.kill.system.api.SpeedKillOrderInfoService;
import com.aikeeper.speed.kill.system.api.SpeedKillService;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillUserDTO;
import com.aikeeper.speed.kill.system.domain.vo.OrderInfoVO;
import com.aikeeper.speed.kill.system.domain.vo.SpeedKillGoodsInfoVO;
import com.aikeeper.speed.kill.system.domain.vo.SpeedKillOrderInfoVO;
import com.aikeeper.speed.kill.system.domain.vo.SpeedKillUserVO;
import com.aikeeper.speed.kill.system.result.CodeMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 19:51
 * @Version V1.0
 **/
@Controller
@RequestMapping("/speed/kill")
public class SpeedKillController {

    @Resource
    private GoodsInfoService goodsInfoService;

    @Resource
    private SpeedKillGoodsInfoService speedKillGoodsInfoService;

    @Resource
    private SpeedKillOrderInfoService speedKillOrderInfoService;

    @Resource
    private SpeedKillService speedKillService;

    @RequestMapping("/do_speed_kill")
    public String toList(@RequestParam("goodsId") Long goodsId, Model model, SpeedKillUserDTO speedKillUserDTO) {

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

    private static SpeedKillUserVO dtoToVo(SpeedKillUserDTO speedKillUserDTO) {
        SpeedKillUserVO speedKillUserVO = new SpeedKillUserVO();
        if (!ObjectUtils.isEmpty(speedKillUserDTO)) {
            BeanUtils.copyProperties(speedKillUserDTO, speedKillUserVO);
        }
        return speedKillUserVO;
    }
}
