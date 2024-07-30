package com.ptu.mall.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {
    private Integer id;

    private String code;

    private String title;

    private Integer categoryId;

    private String categoryName;

    private String img;

    private BigDecimal price;

    private Integer stocks;

    private String description;
}
