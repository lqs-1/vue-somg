package com.lqs.authapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqs.authapi.domain.Role;
import utils.Pagination.PageUtils;
import utils.R;

import java.util.List;
import java.util.Map;

public interface RoleService extends IService<Role> {

    PageUtils getRolePage(Map<String, Object> param);

    R addRole(Role role);

    void editRole(Role role);

    void deleteRoleById(Long id);

    List<Role> getList();

    List<Role> selectCommonRole(String commonRole);
}
