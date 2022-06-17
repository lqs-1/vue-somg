package com.lqs.authapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqs.authapi.domain.UserRole;

import java.util.List;

public interface UserRoleService extends IService<UserRole> {

    List<Long> selectByUserId(Long id);

    void addUserRoleRenation(Long userId, List<Long> roleIds);

    void deleteUserRoleRelation(Long id);

}
