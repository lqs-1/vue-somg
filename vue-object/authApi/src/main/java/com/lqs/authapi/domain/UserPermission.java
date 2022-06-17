package com.lqs.authapi.domain;

import lombok.Data;

@Data
public class UserPermission {

    private Long id;

    private Long userId;

    private Long permissionId;

}
