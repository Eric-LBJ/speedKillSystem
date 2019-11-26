package com.aikeeper.speed.kill.system.api;

import com.aikeeper.speed.kill.system.domain.info.User;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/23 23:26
 * @Version V1.0
 **/
public interface UserService {

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 新增用户信息
     * @return
     */
    Boolean insertUser();

}
