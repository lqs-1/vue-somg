package com.lqs.yebapi.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqs.yebapi.domain.User;
import com.lqs.yebapi.mapper.UserMapper;
import com.lqs.yebapi.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public User getUser(User user) throws Exception {
        User querUser = this.baseMapper.selectByUserName(user.getUsername());
        if (!querUser.getPassword().equals(user.getPassword())) {
            throw new Exception("密码错误");
        }
        return querUser;
    }
}
