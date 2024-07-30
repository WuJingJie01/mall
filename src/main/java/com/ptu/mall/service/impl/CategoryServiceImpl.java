package com.ptu.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ptu.mall.domain.dto.CategoryDTO;
import com.ptu.mall.domain.dto.CategoryUpdateDTO;
import com.ptu.mall.domain.dto.PageQueryDTO;
import com.ptu.mall.domain.po.Category;
import com.ptu.mall.domain.vo.CategoryVO;
import com.ptu.mall.mapper.CategoryMapper;
import com.ptu.mall.service.ICategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Override
    public List<CategoryVO> pageQuery(PageQueryDTO queryDTO) {
        Page<Category> page = lambdaQuery().page(Page.of(queryDTO.getPageNum(), queryDTO.getPageSize()));
        List<Category> records = page.getRecords();
        return BeanUtil.copyToList(records, CategoryVO.class);
    }

    @Override
    public CategoryVO getCategoryById(Integer id) {
        Category category = getById(id);
        return BeanUtil.copyProperties(category, CategoryVO.class);
    }

    @Override
    public boolean add(CategoryDTO categoryDTO) {
        Category category = BeanUtil.copyProperties(categoryDTO, Category.class);
        return save(category);
    }

    @Override
    public boolean updateCategory(CategoryUpdateDTO updateDTO) {
        Category category = BeanUtil.copyProperties(updateDTO, Category.class);
        return updateById(category);
    }

}
