package com.aikeeper.speed.kill.system.comm.keyclass.impl.child;

import com.aikeeper.speed.kill.system.comm.Constans;
import com.aikeeper.speed.kill.system.comm.keyclass.impl.BasePrefix;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 19:37
 * @Version V1.0
 **/
public class GoodsKey extends BasePrefix {

    public GoodsKey(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodsKey goodsListKey = new GoodsKey(Constans.HTML_PAGE_EXPIRE, "gl");

    public static GoodsKey goodsDetailKey = new GoodsKey(Constans.HTML_PAGE_EXPIRE, "gd");
}
