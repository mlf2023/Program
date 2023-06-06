package com.ztt.mt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ztt.mt.pojo.Category;
import com.ztt.mt.service.CategoryService;
import com.ztt.mt.utils.R;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜品及套餐分类(Category)表控制层
 *
 */
@RestController
@RequestMapping("category")
public class CategoryController {
    /**
     * 服务对象
     */
    @Autowired
    private CategoryService categoryService;

    //添加菜品套餐的分类信息
    @PostMapping
    public R<String> add(@RequestBody Category category){
        categoryService.save(category);
        return R.success("添加成功！");
    }

    //分页查询菜品 信息
    @GetMapping("/page")
    public R<IPage<Category>> page(@RequestParam Integer page,
                                   @RequestParam Integer pageSize){
        IPage<Category> iPage = new Page<>(page, pageSize);
        categoryService.page(iPage);
        return R.success(iPage);
    }

    @PutMapping
    public R<String> update(@RequestBody Category category){
        categoryService.updateById(category);
        return R.success("修改成功！");
    }
    @DeleteMapping
    public R<String> removeById(@RequestParam(value = "id") Long cateId){
        categoryService.removeByCateId(cateId);
        return R.success("删除成功！");
    }
    @GetMapping("/list")
    public R<List<Category>> list(@RequestParam(required = false) Integer type){
        LambdaQueryWrapper<Category> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(type!=null,Category::getType,type).orderByDesc(Category::getUpdateTime);
        List<Category> categories=categoryService.list(wrapper);
        return R.success(categories);
    }
}
