package com.ztt.mt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ztt.mt.common.CustomException;
import com.ztt.mt.pojo.Dish;
import com.ztt.mt.pojo.Setmeal;
import  com.ztt.mt.service.CategoryService;
import com.ztt.mt.mapper.CategoryMapper;
import com.ztt.mt.pojo.Category;
import com.ztt.mt.service.DishFlavorService;
import com.ztt.mt.service.DishService;
import com.ztt.mt.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 菜品及套餐分类(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@Service
@Transactional
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    DishService dishService;
    @Autowired
    SetmealService setmealService;


    @Override
    public void removeByCateId(Long cateId) {
        LambdaQueryWrapper<Dish> dishWrapper = new LambdaQueryWrapper<>();
        dishWrapper.eq(Dish::getCategoryId, cateId);
        Long dishCount = dishService.count(dishWrapper);
        if (dishCount > 0) {
            throw new CustomException("当前分类下有菜品信息，不能删除！");
        }
        LambdaQueryWrapper<Setmeal> setMealWrapper = new LambdaQueryWrapper<>();
        setMealWrapper.eq(Setmeal::getCategoryId, cateId);
        long count = setmealService.count(setMealWrapper);
        if (count > 0) {
            throw new CustomException("当前分类下有套餐信息，不能删除！");
        }
        removeById(cateId);
    }
}

