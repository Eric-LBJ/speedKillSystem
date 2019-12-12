package com.aikeeper.speed.kill.system.comm.keyclass.impl.child;

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

    public static SpeedKillKey goodsOverKey = new SpeedKillKey("go");
}
