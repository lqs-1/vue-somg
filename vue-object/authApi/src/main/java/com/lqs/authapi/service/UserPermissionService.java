package com.lqs.authapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqs.authapi.domain.UserPermission;

import java.util.List;

public interface UserPermissionService extends IService<UserPermission> {
    List<Long> selectByUserId(Long id);

    void addUserPermissionRelation(Long userId, List<Long> permissionIds);
}
