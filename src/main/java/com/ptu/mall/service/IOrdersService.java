package com.ptu.mall.service;

import com.ptu.mall.domain.po.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ptu.mall.domain.vo.ResponseResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author
 * @since 2024-09-03
 */
public interface IOrdersService extends IService<Orders> {

    ResponseResult pageQuery(Integer userId, Integer pageNum, Integer pageSize);
}
