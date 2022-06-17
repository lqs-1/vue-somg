package com.lqs.authapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqs.authapi.domain.Permission;
import utils.Pagination.PageUtils;
import utils.R;

import java.util.List;
import java.util.Map;


public interface PermissionService extends IService<Permission> {
    PageUtils getPermissionPage(Map<String, Object> param);

    R addPermission(Permission permission);

    void editPermission(Permission permission);

    void deletePermissionById(Long id);

    List<Permission> getList();

    List<Permission> selectCommonPermission(String commonPermission);
}
