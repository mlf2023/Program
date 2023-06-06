package com.ztt.mt.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ztt.mt.dto.SetmealDTO;
import com.ztt.mt.pojo.SetmealDish;
import com.ztt.mt.service.SetmealDishService;
import  com.ztt.mt.service.SetmealService;
import com.ztt.mt.mapper.SetmealMapper;
import com.ztt.mt.pojo.Setmeal;
import com.ztt.mt.utils.BaseContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 套餐(Setmeal)表服务实现类
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@Transactional
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    SetmealDishService setmealDishService;
    @Override
    public void saveSetmeal(SetmealDTO setmealDTO) {
        this.save(setmealDTO);
        List<SetmealDish> setmealDishes=setmealDTO.getSetmealDishes().stream().map(setmealDish -> {
            setmealDish.setSetmealId((setmealDTO.getId()));
            return setmealDish;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishes);
    }

    @Override
    public void deleteSetmeals(List<Long> ids) {

        for (Long id : ids) {
            LambdaUpdateWrapper<SetmealDish> wrapper=new LambdaUpdateWrapper<>();
            wrapper.eq(SetmealDish::getSetmealId,id);
            setmealDishService.remove(wrapper);
            this.removeById(id);
        }


    }

    @Override
    public void updateSetDto(SetmealDTO setmealDTO) {
        LambdaUpdateWrapper<SetmealDish> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(SetmealDish::getSetmealId,setmealDTO.getId());
        setmealDishService.remove(wrapper);
        this.updateById(setmealDTO);
        for (SetmealDish setmealDish : setmealDTO.getSetmealDishes()) {
            setmealDish.setSetmealId(setmealDTO.getId());
            setmealDishService.save(setmealDish);
        }
    }
}

