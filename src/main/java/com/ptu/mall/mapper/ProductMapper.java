package com.ptu.mall.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ptu.mall.domain.po.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ptu.mall.domain.vo.ProductVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WuJingJie
 * @since 2024-07-28
 */
public interface ProductMapper extends BaseMapper<Product> {
    List<ProductVO> pageQuery(Page<ProductVO> page, @Param("ew") LambdaQueryWrapper<Product> wrapper);

    ProductVO getVOById(Integer id);
}
