package com.ptu.mall.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("用户注册入参")
@Data
public class UserRegDTO {
    private String phone;

    private String password;

    private String password2;

    private String verifyCode;


}
