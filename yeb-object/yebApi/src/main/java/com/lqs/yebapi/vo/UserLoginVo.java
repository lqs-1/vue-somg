package com.lqs.yebapi.vo;

import com.lqs.yebapi.domain.User;
import lombok.Data;

import java.util.List;

@Data
public class UserLoginVo extends User {

    private String code;
}
