package com.aikeeper.speed.kill.system.controller;

import com.aikeeper.speed.kill.system.api.GoodsInfoService;
import com.aikeeper.speed.kill.system.api.OrderInfoService;
import com.aikeeper.speed.kill.system.comm.SpeedKillSupport;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillUserDTO;
import com.aikeeper.speed.kill.system.domain.vo.GoodsInfoVO;
import com.aikeeper.speed.kill.system.domain.vo.OrderDetailVo;
import com.aikeeper.speed.kill.system.domain.vo.OrderInfoVO;
import com.aikeeper.speed.kill.system.domain.vo.SpeedKillUserVO;
import com.aikeeper.speed.kill.system.result.CodeMessage;
import com.aikeeper.speed.kill.system.result.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 19:51
 * @Version V1.0
 **/
@Controller
@RequestMapping("/order")
public class OrderController extends SpeedKillSupport {

    @Resource
    private GoodsInfoService goodsInfoService;

    @Resource
    private OrderInfoService orderInfoService;

    @RequestMapping("/orderDetail")
    @ResponseBody
    public Result<OrderDetailVo> cacheKill(@RequestParam("orderId") Long orderId, SpeedKillUserVO speedKillUserVO) {

        super.checkUser(speedKillUserVO);
        /**
         * 判断订单是否存在
         */
        OrderInfoVO orderInfoVO = orderInfoService.selectByPrimaryKey(orderId);
        if (ObjectUtils.isEmpty(orderInfoVO) && ObjectUtils.isEmpty(orderInfoVO.getId())) {
            return Result.error(CodeMessage.ORDER_NOT_EXIST);
        }
        /**
         * 获取订单相关的商品信息
         */
        GoodsInfoVO goodsInfoVO = goodsInfoService.selectByPrimaryKey(orderInfoVO.getGoodsId());
        if (ObjectUtils.isEmpty(goodsInfoVO) && ObjectUtils.isEmpty(goodsInfoVO.getId())) {
            return Result.error(CodeMessage.GOODS_OF_ORDER_NOT_EXIST);
        }

        OrderDetailVo vo = new OrderDetailVo();
        vo.setGoodsInfoVO(goodsInfoVO);
        vo.setOrderInfoVO(orderInfoVO);
        return Result.success(vo);
    }
}
