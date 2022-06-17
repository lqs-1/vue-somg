package com.lqs.yebapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqs.yebapi.domain.Role;
import com.lqs.yebapi.domain.UserRole;

import java.util.List;

public interface UserRoleService extends IService<UserRole> {

    List<Long> selectByUserId(Long id);

    void addUserRoleRenation(Long userId, List<Long> roleIds);

    void deleteUserRoleRelation(Long id);

}
