package com.who.warehousesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserSaveDto {

    private Integer roleId;
    private String name;
    private String username;
    private String password;
    private String confirm;
}
