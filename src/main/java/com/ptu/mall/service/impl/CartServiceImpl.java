package com.ptu.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ptu.mall.domain.dto.PageQueryDTO;
import com.ptu.mall.domain.po.Cart;
import com.ptu.mall.domain.vo.CategoryVO;
import com.ptu.mall.mapper.CartMapper;
import com.ptu.mall.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {



}
