package com.ptu.mall.service;

import com.ptu.mall.domain.dto.CartPageDTO;
import com.ptu.mall.domain.dto.PageQueryDTO;
import com.ptu.mall.domain.po.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ptu.mall.domain.vo.CartVO;
import com.ptu.mall.domain.vo.CategoryVO;
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
public interface ICartService extends IService<Cart> {
    List<CartVO> pageQuery(CartPageDTO pageDTO);

    CartVO getCartVOById(Integer id);

    ResponseResult add(Integer prodId, Integer num, Integer userId);
}
