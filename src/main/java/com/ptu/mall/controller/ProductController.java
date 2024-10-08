package com.ptu.mall.controller;


import com.ptu.mall.domain.dto.ProductDTO;
import com.ptu.mall.domain.dto.ProductPageQueryDTO;
import com.ptu.mall.domain.po.Product;
import com.ptu.mall.domain.vo.ProductVO;
import com.ptu.mall.domain.vo.ResponseResult;
import com.ptu.mall.service.IProductService;
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
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;
    @GetMapping("/list")
    public ResponseResult pageQuery(ProductPageQueryDTO queryDTO) {
        List<ProductVO> productVOS = productService.searchListGood(queryDTO);
        return ResponseResult.okResult(productVOS);
    }
    @GetMapping("/admin/{id}")
    public ResponseResult getById(@PathVariable Integer id) {
        ProductVO productVO = productService.getProductById(id);
        return ResponseResult.okResult(productVO);
    }

    @PostMapping("/admin/insert")
    public ResponseResult add(@RequestBody ProductDTO productDTO) {
        // TODO：入参要改，等申耀枫的前端说一下他要什么数据
        return productService.add(productDTO);
    }

    @PostMapping("/admin/update")
    public ResponseResult update(@RequestBody ProductDTO productDTO) {
        // TODO：入参要改，等申耀枫的前端说一下他要什么数据
        boolean isUpdated = productService.updateProduct(productDTO);
        if (isUpdated) {
            return ResponseResult.okResult();
        }
        return ResponseResult.failResult("更新失败");
    }

    @PostMapping("/admin/delete")
    public ResponseResult delete(@RequestBody List<Integer> ids) {
        boolean flag = productService.removeBatchByIds(ids);
        if (flag) {
            return ResponseResult.okResult("删除成功");
        }
        return ResponseResult.failResult("删除失败");
    }

}
