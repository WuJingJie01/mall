package com.ptu.mall.controller;


import com.ptu.mall.domain.dto.CategoryDTO;
import com.ptu.mall.domain.dto.CategoryUpdateDTO;
import com.ptu.mall.domain.dto.PageQueryDTO;
import com.ptu.mall.domain.vo.CategoryVO;
import com.ptu.mall.domain.vo.ResponseResult;
import com.ptu.mall.service.ICartService;
import com.ptu.mall.service.ICategoryService;
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
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;
    @GetMapping("/list")
    public ResponseResult getCategories(PageQueryDTO queryDTO) {
         List<CategoryVO> res = categoryService.pageQuery(queryDTO);
         return ResponseResult.okResult(res);
    }

    @GetMapping("/admin/{id}")
    public ResponseResult getById(@PathVariable Integer id) {
        CategoryVO categoryVO = categoryService.getCategoryById(id);
        if (categoryVO == null) {
            return ResponseResult.failResult("查找失败");
        }
        return ResponseResult.okResult(categoryVO);
    }

    @PostMapping("/admin/insert")
    public ResponseResult addCategory(@RequestBody CategoryDTO categoryDTO) {
        boolean flag = categoryService.add(categoryDTO);
        if (flag) {
            return ResponseResult.okResult();
        }
        return ResponseResult.failResult("添加分类失败");
    }

    @PostMapping("/admin/update")
    public ResponseResult update(@RequestParam Integer id,
                                 @RequestParam String name) {
        if (id == null) {
            return ResponseResult.failResult("id不能为空");
        }
        boolean flag = categoryService.updateCategory(id, name);
        if (flag) {
            return ResponseResult.okResult();
        }
        return ResponseResult.failResult("更新失败");
    }

    @PostMapping("/admin/delete")
    public ResponseResult deleteBatches(@RequestBody List<Integer> ids) {
        if (ids.isEmpty()) {
            return ResponseResult.failResult("参数为空");
        }
        boolean flag = categoryService.removeBatchByIds(ids);
        if (flag) {
            return ResponseResult.okResult();
        }
        return ResponseResult.failResult("删除失败");
    }
}
