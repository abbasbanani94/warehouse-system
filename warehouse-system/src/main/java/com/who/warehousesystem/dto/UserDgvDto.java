package com.who.warehousesystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDgvDto {

    @JsonProperty(value = "User ID")
    private Integer id;
    @JsonProperty(value = "Role")
    private String roleName;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Active")
    private Boolean active;
}
