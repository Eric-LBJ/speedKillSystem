package com.aikeeper.speed.kill.system.dal;

import com.aikeeper.speed.kill.system.domain.info.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/23 22:56
 * @Version V1.0
 **/
@Mapper
public interface UserMapper {

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") Long id);

    /**
     * 插入用户信息
     *
     * @param user
     * @return
     */
    @Insert("insert into user set id = #{id} , name = #{name}")
    Integer insertUser(User user);
}
