package com.ptu.mall.domain.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "登录入参DTO")
@Data
public class UserDTO implements Serializable {
    private String phone;
    private String password;
    private String verifyCode;
}
