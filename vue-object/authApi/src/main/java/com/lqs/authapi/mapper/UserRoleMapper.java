package com.lqs.authapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqs.authapi.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    List<Long> selectByUserId(@Param("userId") Long id);
}
