package com.lqs.yebapi.vo;

import com.lqs.yebapi.domain.User;
import lombok.Data;

import java.util.List;

@Data
public class UserVo extends User {

    private List<Long> roleIds;
}
