package com.ptu.mall.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@ApiModel("用户登录返回结果VO")
@Builder
@Data
public class UserVO {
    private Integer id;

    private String phone;

    private String nickName;
}
