package com.aikeeper.speed.kill.system.comm.keyclass.impl.child;

import com.aikeeper.speed.kill.system.comm.Constans;
import com.aikeeper.speed.kill.system.comm.keyclass.impl.BasePrefix;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/12/12 9:32
 * @Version V1.0
 **/
public class SpeedKillKey extends BasePrefix {

    public SpeedKillKey(String prefix) {
        super(prefix);
    }

    public SpeedKillKey(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SpeedKillKey goodsOverKey = new SpeedKillKey("go");

    public static SpeedKillKey requestPathKey = new SpeedKillKey(Constans.DEFAULT_EXPIRE, "rpk");

    public static SpeedKillKey verifyCodeKey = new SpeedKillKey(Constans.DEFAULT_EXPIRE, "vck");
}
