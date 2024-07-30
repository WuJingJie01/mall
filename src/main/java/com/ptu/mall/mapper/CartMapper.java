package com.ptu.mall.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ptu.mall.domain.po.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ptu.mall.domain.vo.CartVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WuJingJie
 * @since 2024-07-28
 */
public interface CartMapper extends BaseMapper<Cart> {
    @Select("select c.*, p.title, p.img, p.price " +
            "from cart c " +
            "left join product p on c.prod_id = p.id " +
            "where c.user_id = #{userId}")
    List<CartVO> pageQuery(Page<Cart> page, Integer userId);
    @Select("select c.*, p.title, p.img, p.price " +
            "from cart c " +
            "left join product p on c.prod_id = p.id " +
            "where c.id = #{id}")
    CartVO queryVOById(Integer id);
}
