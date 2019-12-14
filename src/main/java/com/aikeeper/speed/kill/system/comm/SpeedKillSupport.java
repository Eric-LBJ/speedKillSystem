package com.aikeeper.speed.kill.system.comm;

import com.aikeeper.speed.kill.system.domain.vo.SpeedKillUserVO;
import com.aikeeper.speed.kill.system.exception.GlobalException;
import com.aikeeper.speed.kill.system.result.CodeMessage;
import org.springframework.util.ObjectUtils;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/12/14 15:34
 * @Version V1.0
 **/
public class SpeedKillSupport {

    /**
     * 校验是否登陆
     *
     * @param speedKillUserVO
     * @return
     */
    public SpeedKillSupport checkUser(SpeedKillUserVO speedKillUserVO) {
        if (ObjectUtils.isEmpty(speedKillUserVO)) {
            throw new GlobalException(CodeMessage.SESSION_ERROR);
        }
        return this;
    }


}
