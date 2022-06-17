package com.lqs.yebapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqs.yebapi.domain.Role;
import utils.Pagination.PageUtils;
import utils.R;

import java.util.List;
import java.util.Map;

public interface RoleService extends IService<Role> {

    PageUtils getUserPage(Map<String, Object> param);

    R addRole(Role role);

    void editRole(Role role);

    void deleteUserById(Long id);

    List<Role> getList();

    List<Role> selectCommonRole(String commonRole);
}
