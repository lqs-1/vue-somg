package com.lqs.authapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqs.authapi.constant.REnum;
import com.lqs.authapi.domain.Permission;
import com.lqs.authapi.domain.Role;
import com.lqs.authapi.mapper.PermissionMapper;
import com.lqs.authapi.service.PermissionService;
import org.springframework.stereotype.Service;
import utils.Pagination.PageUtils;
import utils.Pagination.QueryPage;
import utils.R;

import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Override
    public PageUtils getPermissionPage(Map<String, Object> param) {

        IPage<Permission> page = this.page(new QueryPage<Permission>().getPage(param, true),
                new LambdaQueryWrapper<Permission>().like(Permission::getPermissionName,
                        (String) param.get("permissionname")));

        return new  PageUtils(page);
    }

    @Override
    public R addPermission(Permission permission) {

        Permission queryPermission = this.baseMapper.getPermissionByPermissionName(permission.getPermissionName());

        if (queryPermission != null){

            return R.error(REnum.PERMISSION_DOES_EXIST.getStatusCode(),
                    REnum.PERMISSION_DOES_EXIST.getStatusMsg());

        }

        this.baseMapper.insert(permission);

        return R.ok(REnum.PERMISSION_ADD_SUCCESS.getStatusCode(),
                REnum.PERMISSION_ADD_SUCCESS.getStatusMsg());

    }

    @Override
    public void editPermission(Permission permission) {

        this.baseMapper.updateById(permission);

    }

    @Override
    public void deletePermissionById(Long id) {

        this.baseMapper.deleteById(id);

    }

    @Override
    public List<Permission> getList() {

        List<Permission> permissionList = this.baseMapper.selectList(null);

        return permissionList;

    }

    @Override
    public List<Permission> selectCommonPermission(String commonPermission) {

        List<Permission> permissionList = this.baseMapper.selectList(new LambdaQueryWrapper<Permission>().eq(Permission::getPermissionName, commonPermission));

        if (permissionList.size() == 1){

            return permissionList;

        }

        return null;
    }
}
