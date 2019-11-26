package com.aikeeper.speed.kill.system.service;

import com.aikeeper.speed.kill.system.api.UserService;
import com.aikeeper.speed.kill.system.dal.UserMapper;
import com.aikeeper.speed.kill.system.domain.info.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/23 22:58
 * @Version V1.0
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertUser() {
        User firstUser = new User((long) 2, "Mrs.guo");
        User secondUser = new User((long) 1, "Mrs.yin");
        Integer result = userMapper.insertUser(firstUser);
        result += userMapper.insertUser(secondUser);
        return result == 2 ? Boolean.TRUE : Boolean.FALSE;
    }
}
