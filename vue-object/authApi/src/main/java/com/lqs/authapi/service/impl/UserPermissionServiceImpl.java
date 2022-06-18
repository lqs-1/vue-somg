package com.lqs.authapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqs.authapi.domain.UserPermission;
import com.lqs.authapi.mapper.UserPermissionMapper;
import com.lqs.authapi.service.UserPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermission> implements UserPermissionService {
    @Override
    public List<Long> selectByUserId(Long id) {

        List<Long> permissionIdList = this.baseMapper.selectByUserId(id);


        return permissionIdList;
    }

    @Override
    public void addUserPermissionRelation(Long userId, List<Long> permissionIds) {

        if (permissionIds != null){

            this.baseMapper.delete(new LambdaQueryWrapper<UserPermission>()
                    .eq(UserPermission::getUserId, userId));

            for (Long permissionId : permissionIds) {

                UserPermission userPermission = new UserPermission();

                userPermission.setUserId(userId);

                userPermission.setPermissionId(permissionId);

                this.baseMapper.insert(userPermission);

            }
        }

    }

    @Override
    public void deleteUserPermissionRelation(Long id) {
        if (id != null){

            this.baseMapper.delete(new LambdaQueryWrapper<UserPermission>()
                    .eq(UserPermission::getUserId, id));

        }
    }
}
