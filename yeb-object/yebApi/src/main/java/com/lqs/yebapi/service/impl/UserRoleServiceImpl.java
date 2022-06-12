package com.lqs.yebapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqs.yebapi.domain.UserRole;
import com.lqs.yebapi.mapper.UserRoleMapper;
import com.lqs.yebapi.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Override
    public List<Long> selectByUserId(Long id) {
        List<Long> roleIdList = this.baseMapper.selectByUserId(id);
        return roleIdList;
    }
}
