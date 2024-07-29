package com.ptu.mall.domain.dto;

import lombok.Data;


@Data
public class PageQueryDTO {
    Integer pageNum = 1;
    Integer pageSize = 10;
}
