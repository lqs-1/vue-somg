package com.lqs.authapi.vo;

import com.lqs.authapi.domain.User;
import lombok.Data;

import java.util.List;

@Data
public class UserVo extends User {

    private List<Long> roleIds;
}
