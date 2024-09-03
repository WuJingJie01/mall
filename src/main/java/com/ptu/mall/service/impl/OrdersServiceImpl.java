package com.ptu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ptu.mall.domain.po.Orders;
import com.ptu.mall.domain.vo.ResponseResult;
import com.ptu.mall.mapper.OrdersMapper;
import com.ptu.mall.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author
 * @since 2024-09-03
 */
@Service
@RequiredArgsConstructor
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {
    private final OrdersMapper ordersMapper;
    @Override
    public ResponseResult pageQuery(Integer userId, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<Orders>().
                eq(Orders::getUserId, userId);
        Page<Orders> page = ordersMapper.selectPage(Page.of(pageNum, pageSize), wrapper);
        List<Orders> records = page.getRecords();
        return ResponseResult.okResult(records);
    }
}
