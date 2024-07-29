package com.ptu.mall.service;

import com.ptu.mall.domain.dto.PageQueryDTO;
import com.ptu.mall.domain.po.Banner;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ptu.mall.domain.vo.BannerVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WuJingJie
 * @since 2024-07-28
 */
public interface IBannerService extends IService<Banner> {


    List<BannerVO> getList(PageQueryDTO queryDTO);
}
