package com.ztt.mt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ztt.mt.common.CustomException;
import com.ztt.mt.dto.DishDTO;
import com.ztt.mt.pojo.DishFlavor;
import com.ztt.mt.pojo.SetmealDish;
import com.ztt.mt.service.DishFlavorService;
import  com.ztt.mt.service.DishService;
import com.ztt.mt.mapper.DishMapper;
import com.ztt.mt.pojo.Dish;
import com.ztt.mt.service.SetmealDishService;
import com.ztt.mt.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品管理(Dish)表服务实现类
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@Transactional
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    DishFlavorService dishFlavorService;
    @Autowired
    SetmealDishService setmealDishService;
    @Override
    public void saveFlavor(DishDTO dishDTO) {
        this.save(dishDTO);
        List<DishFlavor> flavorList=dishDTO.getFlavors().stream().map(dishFlavor -> {
            dishFlavor.setDishId(dishDTO.getId());
            return dishFlavor;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavorList);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        for (Long id : ids) {
            if (this.getById(id).getStatus()==0) {
                LambdaQueryWrapper<SetmealDish> wrapper=new LambdaQueryWrapper<>();
                wrapper.eq(SetmealDish::getDishId,id);
                if(setmealDishService.count(wrapper)>0){
                    throw new CustomException("存在套餐内包含该菜品！");
                }
                LambdaUpdateWrapper<DishFlavor> wrappers=new LambdaUpdateWrapper<>();
                wrappers.eq(DishFlavor::getDishId,id);
                dishFlavorService.remove(wrappers);
                this.removeById(id);
            }
            else throw new CustomException("部分菜品未下架！");
        }
    }

    @Override
    public void updateFlavor(DishDTO dishDTO) {
        this.updateById(dishDTO);
        LambdaUpdateWrapper<DishFlavor> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(DishFlavor::getDishId,dishDTO.getId());
        dishFlavorService.remove(wrapper);
        List<DishFlavor> flavorList=dishDTO.getFlavors().stream().map(dishFlavor -> {
            dishFlavor.setDishId(dishDTO.getId());
            return dishFlavor;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavorList);
    }
}

