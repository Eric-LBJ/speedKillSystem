package com.aikeeper.speed.kill.system.comm.keyclass.impl.child;

import com.aikeeper.speed.kill.system.comm.keyclass.impl.BasePrefix;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 10:24
 * @Version V1.0
 **/
public class UserKey extends BasePrefix {

    public UserKey(String prefix) {
        super(prefix);
    }

    /**
     * 根据id获取key
     */
    public static UserKey getById = new UserKey("id");

    /**
     * 根据name获取key
     */
    public static UserKey getByName = new UserKey("name");
}
