package com.ptu.mall.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerVO implements Serializable {
    private Integer id;
    private String img;
    private String url;
    private String description;
}
