package com.ptu.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ptu.mall.domain.dto.BannerDTO;
import com.ptu.mall.domain.dto.PageQueryDTO;
import com.ptu.mall.domain.po.Banner;
import com.ptu.mall.domain.vo.BannerVO;
import com.ptu.mall.mapper.BannerMapper;
import com.ptu.mall.service.IBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Override
    public List<BannerVO> getList(PageQueryDTO queryDTO) {
        Page<Banner> page = page(Page.of(queryDTO.getPageNum(), queryDTO.getPageSize()));
        List<Banner> records = page.getRecords();
        return BeanUtil.copyToList(records, BannerVO.class);
    }

    @Override
    public boolean saveBanner(BannerDTO bannerDTO) {
        Banner banner = BeanUtil.copyProperties(bannerDTO, Banner.class);
        return save(banner);
    }

    @Override
    public boolean updateBanner(BannerDTO bannerDTO) {
        Banner banner = BeanUtil.copyProperties(bannerDTO, Banner.class);
        return updateById(banner);
    }
}
