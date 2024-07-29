package com.ptu.mall.service.impl;

import com.ptu.mall.domain.po.Banner;
import com.ptu.mall.mapper.BannerMapper;
import com.ptu.mall.service.IBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WuJingJie
 * @since 2024-07-28
 */
@Service
@RequiredArgsConstructor
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements IBannerService {
    private final BannerMapper bannerMapper;

}
