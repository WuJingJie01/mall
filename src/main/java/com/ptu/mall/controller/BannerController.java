package com.ptu.mall.controller;


import cn.hutool.core.bean.BeanUtil;
import com.ptu.mall.domain.dto.BannerDTO;
import com.ptu.mall.domain.dto.PageQueryDTO;
import com.ptu.mall.domain.po.Banner;
import com.ptu.mall.domain.vo.BannerVO;
import com.ptu.mall.domain.vo.ResponseResult;
import com.ptu.mall.service.IBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    public ResponseResult pageQuery(PageQueryDTO queryDTO) {
        List<BannerVO> bannerVOList = bannerService.getList(queryDTO);
        return ResponseResult.okResult(bannerVOList);
    }

    @GetMapping("/admin/{id}")
    public ResponseResult getById(@PathVariable Integer id) {
        Banner banner = bannerService.getById(id);
        if (banner == null) {
            return ResponseResult.failResult("查询失败");
        }
        BannerVO bannerVO = BeanUtil.copyProperties(banner, BannerVO.class);
        return ResponseResult.okResult(bannerVO);
    }

    @PostMapping("/admin/insert")
    public ResponseResult insert(@RequestBody BannerDTO bannerDTO) {
        boolean flag = bannerService.saveBanner(bannerDTO);

        if (flag) {
            return ResponseResult.okResult();
        }
        return ResponseResult.failResult("添加失败");
    }

    @PostMapping("/admin/update")
    public ResponseResult update(@RequestBody BannerDTO bannerDTO) {
        if (bannerDTO.getId() == null) {
            return ResponseResult.failResult("id不能为空");
        }
        boolean flag = bannerService.updateBanner(bannerDTO);
        if (flag) {
            return ResponseResult.okResult();
        }
        return ResponseResult.failResult("更新失败");
    }

    @PostMapping("/admin/delete")
    public ResponseResult deleteByIds(@RequestBody List<Integer> ids) {
        boolean flag = bannerService.removeBatchByIds(ids);
        if (flag) {
            return ResponseResult.okResult();
        }
        return ResponseResult.failResult("删除失败");
    }
}
