package com.lqs.authapi.vo;

import com.lqs.authapi.domain.User;
import lombok.Data;

@Data
public class UserLoginVo extends User {

    private String code;
}
