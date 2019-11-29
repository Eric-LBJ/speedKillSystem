package com.aikeeper.speed.kill.system.controller;

import com.aikeeper.speed.kill.system.api.GoodsInfoService;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillUserDTO;
import com.aikeeper.speed.kill.system.domain.vo.GoodsInfoVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 19:51
 * @Version V1.0
 **/
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsInfoService goodsInfoService;

    /**
     * QPS:236
     * 并发：5000 循环10次
     */
    @RequestMapping("/to_list")
    public String toList(Model model) {
        model.addAttribute("goodsList", goodsInfoService.listSpeedKillGoods());
        return "goods_list";
    }

    @RequestMapping("/to_detail/{id}")
    public String toDetail(@PathVariable("id") Long id, Model model, SpeedKillUserDTO speedKillUserDTO) {
        model.addAttribute("user", speedKillUserDTO);
        GoodsInfoVO vo = goodsInfoService.selectByPrimaryKey(id);
        model.addAttribute("goods", vo);

        Long startTime = vo.getStartDate().getTime();
        Long endTime = vo.getEndDate().getTime();
        Long now = System.currentTimeMillis();

        Integer speedKillStatus = 1;
        Long speedKillSeconds = 0L;
        if (now < startTime) {
            speedKillStatus = 0;
            speedKillSeconds = (startTime - now) / 1000;
        } else if (now > endTime) {
            speedKillStatus = 2;
            speedKillSeconds = -1L;
        }
        model.addAttribute("speedKillStatus", speedKillStatus);
        model.addAttribute("speedKillSeconds", speedKillSeconds);
        return "goods_detail";
    }

}
