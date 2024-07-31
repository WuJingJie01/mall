package com.ptu.mall.service;

import com.ptu.mall.domain.dto.CategoryDTO;
import com.ptu.mall.domain.dto.CategoryUpdateDTO;
import com.ptu.mall.domain.dto.PageQueryDTO;
import com.ptu.mall.domain.po.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ptu.mall.domain.vo.CategoryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WuJingJie
 * @since 2024-07-28
 */
public interface ICategoryService extends IService<Category> {

    List<CategoryVO> pageQuery(PageQueryDTO queryDTO);

    CategoryVO getCategoryById(Integer id);

    boolean add(CategoryDTO categoryDTO);

    boolean updateCategory(Integer id, String name);

}
