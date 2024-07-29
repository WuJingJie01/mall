package com.ptu.mall.domain.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class PageQueryDTO implements Serializable {
    Integer pageNum = 1;
    Integer pageSize = 10;
}
