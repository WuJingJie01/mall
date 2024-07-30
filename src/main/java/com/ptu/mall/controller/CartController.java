package com.ptu.mall.controller;


import com.ptu.mall.domain.dto.CartPageDTO;
import com.ptu.mall.domain.vo.CartVO;
import com.ptu.mall.domain.vo.ResponseResult;
import com.ptu.mall.service.ICartService;
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
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final ICartService cartService;
    @GetMapping("/list")
    public ResponseResult pageQuery(CartPageDTO pageDTO) {
        if (pageDTO.getUserId() == null) {
            return ResponseResult.failResult("UserId不能为空");
        }
        List<CartVO> cartVOList = cartService.pageQuery(pageDTO);

        return ResponseResult.okResult(cartVOList);
    }
    @GetMapping("/admin/{id}")
    public ResponseResult getById(@PathVariable Integer id) {
        CartVO cart = cartService.getCartVOById(id);
        if (cart == null) {
            return ResponseResult.failResult("查询失败");
        }
        return ResponseResult.okResult(cart);
    }

    @PostMapping("/admin/insertOrUpdate")
    public ResponseResult add(@RequestParam(required = false) Integer prodId,
                              @RequestParam(required = false) Integer num,
                              @RequestParam(required = false) Integer userId) {
        if (prodId == null) {
            return ResponseResult.failResult("商品ID不能为空");
        }
        if (userId == null) {
            return ResponseResult.failResult("用户ID不能为空");
        }
        if (num == null) {
            return ResponseResult.failResult("数量不能为空");
        }
        return cartService.add(prodId, num, userId);
    }

    @PostMapping("/admin/delete")
    public ResponseResult delete(@RequestBody List<Integer> ids) {
        boolean flag = cartService.removeBatchByIds(ids);
        if (flag) {
            return ResponseResult.okResult();
        }
        return ResponseResult.failResult();
    }
}
