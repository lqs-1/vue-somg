package com.lqs.authapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqs.authapi.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User selectByUserName(@Param("username") String username);

    User getUserByUserName(@Param("username") String username);

    int alterPwdByUserName(User user);
}
