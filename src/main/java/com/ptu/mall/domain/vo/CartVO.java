package com.ptu.mall.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CartVO implements Serializable {
    @ApiModelProperty("购物车id")
    private Integer id;
    @ApiModelProperty(value = "商品id")
    private Integer prodId;
    @ApiModelProperty(value = "商品数量")
    private Integer num;
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "商品标题")
    private String title;
    @ApiModelProperty(value = "商品图片")
    private String img;
    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;
}
