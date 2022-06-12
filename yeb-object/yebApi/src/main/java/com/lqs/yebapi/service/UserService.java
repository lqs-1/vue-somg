package com.lqs.yebapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqs.yebapi.domain.User;
import utils.Pagination.PageUtils;
import utils.R;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService extends IService<User> {

    R getUser(User user, HttpServletRequest request) throws Exception;

    List<User> getUserList();

    PageUtils getUserPage(Map<String, Object> params);

    void addUser(User user);

    void editUser(User user);

    void deleteUserById(Long id);
}
