package com.aikeeper.speed.kill.system.controller;

import com.aikeeper.speed.kill.system.api.GoodsInfoService;
import com.aikeeper.speed.kill.system.api.RedisService;
import com.aikeeper.speed.kill.system.comm.keyclass.impl.child.GoodsKey;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillUserDTO;
import com.aikeeper.speed.kill.system.domain.vo.GoodsDetailVo;
import com.aikeeper.speed.kill.system.domain.vo.GoodsInfoVO;
import com.aikeeper.speed.kill.system.domain.vo.SpeedKillUserVO;
import com.aikeeper.speed.kill.system.result.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

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

    @Resource
    private RedisService redisService;

    @Resource
    private ThymeleafViewResolver thymeleafViewResolver;

    /**
     * -----------------第一次压测-----------------
     * QPS:236
     * 并发：5000 循环10次
     * <p>
     * -----------------做页面级缓存-----------------
     */
    @RequestMapping(value = "/to_list", produces = "text/html")
    @ResponseBody
    public String toList(HttpServletRequest request, HttpServletResponse response, Model model) {

        /**
         * 先从缓存里面取页面，如果有直接返回
         */
        String html = redisService.get(GoodsKey.goodsListKey, "", String.class);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }
        /**
         * 如果缓存里面没有需要手动渲染
         */
        model.addAttribute("goodsList", goodsInfoService.listSpeedKillGoods());
        WebContext ctx = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        try {
            ctx.getRequest().setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ctx.getResponse().setCharacterEncoding("UTF-8");
        /**手动渲染*/
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
        /**加入缓存*/
        if (!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.goodsListKey, "", html);
        }
        return html;
    }

    @RequestMapping(value = "/to_detail/{id}", produces = "text/html")
    @ResponseBody
    public String toDetail(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response,
                           Model model, SpeedKillUserVO speedKillUserVO) {

        /**
         * 先从缓存里面取页面，如果有直接返回
         */
        String html = redisService.get(GoodsKey.goodsDetailKey, id.toString(), String.class);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }

        model.addAttribute("user", speedKillUserVO);
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

        /**
         * 如果缓存里面没有需要手动渲染
         */
        WebContext ctx = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());

        /**手动渲染*/
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", ctx);
        /**加入缓存*/
        if (!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.goodsDetailKey, id.toString(), html);
        }
        return html;
    }

    @RequestMapping(value = "/detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVo> detail(SpeedKillUserVO speedKillUserVO, @PathVariable("goodsId") long goodsId) {
        GoodsInfoVO goodsInfoVO = goodsInfoService.selectByPrimaryKey(goodsId);
        Long startAt = goodsInfoVO.getStartDate().getTime();
        Long endAt = goodsInfoVO.getEndDate().getTime();
        Long now = System.currentTimeMillis();
        Integer speedKillStatus = 0;
        Integer remainSeconds = 0;

        if (now < startAt) {
            /**
             * 秒杀还没开始，倒计时
             */
            speedKillStatus = 0;
            remainSeconds = (int) ((startAt - now) / 1000);
        } else if (now > endAt) {
            /**
             * 秒杀已经结束
             */
            speedKillStatus = 2;
            remainSeconds = -1;
        } else {
            /**
             * 秒杀进行中
             */
            speedKillStatus = 1;
            remainSeconds = 0;
        }
        GoodsDetailVo vo = new GoodsDetailVo();
        vo.setGoodsInfoVO(goodsInfoVO);
        vo.setSpeedKillUserVO(speedKillUserVO);
        vo.setRemainSeconds(remainSeconds);
        vo.setSpeedKillStatus(speedKillStatus);
        return Result.success(vo);
    }

}
