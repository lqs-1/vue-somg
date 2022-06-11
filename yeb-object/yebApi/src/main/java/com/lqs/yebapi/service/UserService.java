package com.lqs.yebapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqs.yebapi.domain.User;

public interface UserService extends IService<User> {

    User getUser(User user) throws Exception;

}
