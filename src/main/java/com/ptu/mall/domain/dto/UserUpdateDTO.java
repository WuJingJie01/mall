package com.ptu.mall.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdateDTO implements Serializable {
    private Integer id;
    private String nickName;
}
