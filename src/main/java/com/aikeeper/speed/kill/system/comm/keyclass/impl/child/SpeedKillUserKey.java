package com.aikeeper.speed.kill.system.comm.keyclass.impl.child;

import com.aikeeper.speed.kill.system.comm.Constans;
import com.aikeeper.speed.kill.system.comm.keyclass.impl.BasePrefix;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 19:37
 * @Version V1.0
 **/
public class SpeedKillUserKey extends BasePrefix {

    public SpeedKillUserKey(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SpeedKillUserKey token = new SpeedKillUserKey(Constans.TOKEN_EXPIRE, "tk");
}
