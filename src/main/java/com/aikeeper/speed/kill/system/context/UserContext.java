package com.aikeeper.speed.kill.system.context;

import com.aikeeper.speed.kill.system.domain.vo.SpeedKillUserVO;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/12/14 16:50
 * @Version V1.0
 **/
public class UserContext {

    private static final ThreadLocal<SpeedKillUserVO> userHolder = new ThreadLocal<>();

    public static void setUser(SpeedKillUserVO speedKillUserVO) {
        userHolder.set(speedKillUserVO);
    }

    public static SpeedKillUserVO getUser() {
        return userHolder.get();
    }
}
