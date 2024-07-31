package com.ptu.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ptu.mall.domain.dto.ProductDTO;
import com.ptu.mall.domain.dto.ProductPageQueryDTO;
import com.ptu.mall.domain.po.Product;
import com.ptu.mall.domain.vo.ProductVO;
import com.ptu.mall.domain.vo.ResponseResult;
import com.ptu.mall.mapper.ProductMapper;
import com.ptu.mall.service.IProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    private final ProductMapper productMapper;
    @Override
    public List<ProductVO> searchListGood(ProductPageQueryDTO queryDTO) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        Integer categoryId = queryDTO.getCategoryId();
        String title = queryDTO.getTitle();
        wrapper.eq(categoryId != null, Product::getCategoryId, categoryId).
                like(!StrUtil.isBlank(title), Product::getTitle, title);
        Page<ProductVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        return productMapper.pageQuery(page, wrapper);
    }

    @Override
    public ProductVO getProductById(Integer id) {
        return productMapper.getVOById(id);
    }

    @Override
    public ResponseResult add(ProductDTO productDTO) {
        // 判断数据库是否存在该商品编码
        String code = productDTO.getCode();
        if (StrUtil.isBlank(code)) {
            return ResponseResult.failResult("商品编码为空");
        }
        Long count = lambdaQuery().eq(Product::getCode, code).count();
        if (count != 0) {
            return ResponseResult.failResult(501, "商品编码已存在，新增失败");
        }

        Product product = BeanUtil.copyProperties(productDTO, Product.class);
        boolean isSave = save(product);

        if (isSave) {
            return ResponseResult.okResult();
        }

        return ResponseResult.failResult();
    }

    @Override
    public boolean updateProduct(ProductDTO productDTO) {
        Product product = BeanUtil.copyProperties(productDTO, Product.class);
        return updateById(product);
    }

}
