package com.lqs.yebapi.vo;

import com.lqs.yebapi.domain.User;
import lombok.Data;

@Data
public class UserLoginVo extends User {

    private String code;
}
