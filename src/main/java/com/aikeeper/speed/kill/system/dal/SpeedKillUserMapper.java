package com.aikeeper.speed.kill.system.dal;

import com.aikeeper.speed.kill.system.domain.info.SpeedKillUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 14:46
 * @Version V1.0
 **/
@Mapper
public interface SpeedKillUserMapper {

    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
    SpeedKillUser getSpeedKillUserById(@Param("id") Long id);

}
