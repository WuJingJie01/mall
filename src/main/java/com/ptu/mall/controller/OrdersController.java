package com.ptu.mall.controller;


import com.ptu.mall.domain.dto.ProductDTO;
import com.ptu.mall.domain.dto.ProductPageQueryDTO;
import com.ptu.mall.domain.vo.ProductVO;
import com.ptu.mall.domain.vo.ResponseResult;
import com.ptu.mall.service.IOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author
 * @since 2024-09-03
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final IOrdersService ordersService;
    private final HttpSession httpSession;
    // 分页查询
    @GetMapping("/admin/list")
    public ResponseResult pageQuery(Integer pageNum, Integer pageSize) {
        Integer userId = Integer.parseInt(httpSession.getAttribute("userId").toString());
        return ordersService.pageQuery(userId, pageNum, pageSize);

    }

    // 根据id查询

    // 完成订单





}
