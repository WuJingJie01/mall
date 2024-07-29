package com.ptu.mall.controller;


import com.ptu.mall.domain.po.Banner;
import com.ptu.mall.service.IBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WuJingJie
 * @since 2024-07-28
 */
@RestController
@RequestMapping("/banner")
@RequiredArgsConstructor
public class BannerController {
    private final IBannerService bannerService;


}
