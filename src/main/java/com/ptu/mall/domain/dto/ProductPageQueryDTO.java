package com.ptu.mall.domain.dto;

import lombok.Data;

@Data
public class ProductPageQueryDTO {
    private String title;
    private Integer categoryId;
    private Integer pageNum = 1;
    private Integer pageSize = 5;
}
