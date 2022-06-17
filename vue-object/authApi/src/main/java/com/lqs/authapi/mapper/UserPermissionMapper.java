package com.lqs.authapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqs.authapi.domain.UserPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserPermissionMapper extends BaseMapper<UserPermission> {
    List<Long> selectByUserId(@Param("userId") Long id);

}
