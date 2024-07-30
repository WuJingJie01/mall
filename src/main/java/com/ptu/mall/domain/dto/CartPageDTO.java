package com.ptu.mall.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartPageDTO {
    Integer userId;
    Integer pageNum = 1;
    Integer pageSize = 5;
}
