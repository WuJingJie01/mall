package com.ptu.mall.service;

import com.ptu.mall.domain.dto.ProductDTO;
import com.ptu.mall.domain.dto.ProductPageQueryDTO;
import com.ptu.mall.domain.po.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ptu.mall.domain.vo.ProductVO;
import com.ptu.mall.domain.vo.ResponseResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WuJingJie
 * @since 2024-07-28
 */
public interface IProductService extends IService<Product> {

    List<ProductVO> searchListGood(ProductPageQueryDTO queryDTO);

    ProductVO getProductById(Integer id);

    ResponseResult add(ProductDTO productDTO);

    boolean updateProduct(ProductDTO productDTO);
}
