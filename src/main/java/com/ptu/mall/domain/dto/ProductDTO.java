package com.ptu.mall.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    @ApiModelProperty(value = "商品编码，不可重复")
    private String code;

    @ApiModelProperty(value = "商品标题")
    private String title;

    @ApiModelProperty(value = "商品分类id")
    private Integer categoryId;

    @ApiModelProperty(value = "商品图片")
    private String img;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "商品库存")
    private Integer stocks;

    @ApiModelProperty(value = "商品描述")
    private String description;
}
