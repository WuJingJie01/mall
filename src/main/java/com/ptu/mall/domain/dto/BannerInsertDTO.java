package com.ptu.mall.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerInsertDTO {
    private String img;
    private String url;
    private String description;
}
