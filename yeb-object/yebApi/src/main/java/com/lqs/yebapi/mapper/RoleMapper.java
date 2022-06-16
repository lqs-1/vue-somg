package com.lqs.yebapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqs.yebapi.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    Role getRoleByRoleName(@Param("roleName") String roleName);
}
