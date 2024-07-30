package com.ptu.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ptu.mall.domain.dto.CartPageDTO;
import com.ptu.mall.domain.dto.PageQueryDTO;
import com.ptu.mall.domain.po.Cart;
import com.ptu.mall.domain.vo.CartVO;
import com.ptu.mall.domain.vo.CategoryVO;
import com.ptu.mall.domain.vo.ResponseResult;
import com.ptu.mall.mapper.CartMapper;
import com.ptu.mall.service.ICartService;
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
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {
    private final CartMapper cartMapper;
    @Override
    public List<CartVO> pageQuery(CartPageDTO pageDTO) {
        Page<Cart> page = new Page<>(pageDTO.getPageNum(), pageDTO.getPageSize());

        List<CartVO> cartVOS = cartMapper.pageQuery(page, pageDTO.getUserId());

        return cartVOS;
    }

    @Override
    public CartVO getCartVOById(Integer id) {

        return cartMapper.queryVOById(id);
    }

    @Override
    public ResponseResult add(Integer prodId, Integer num, Integer userId) {
        // 先查询是否存在该商品
        Cart cart = lambdaQuery()
                .eq(Cart::getProdId, prodId)
                .eq(Cart::getUserId, userId).one();

        if (cart == null) {
            // 插入商品
            Cart build = Cart.builder()
                    .prodId(prodId)
                    .num(num)
                    .userId(userId).build();
            boolean isSave = save(build);
            if (isSave) {
                return ResponseResult.okResult("新增一条购物车记录");
            } else {
                return ResponseResult.failResult("插入失败");
            }
        }

        // 更新
        cart.setNum(num);
        boolean flag = updateById(cart);
        if (flag) {
            return ResponseResult.okResult("更新一条购物车记录");
        }

        return ResponseResult.failResult();
    }
}
