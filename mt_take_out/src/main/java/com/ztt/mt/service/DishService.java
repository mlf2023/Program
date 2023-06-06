package com.ztt.mt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ztt.mt.dto.DishDTO;
import com.ztt.mt.pojo.Dish;

import java.util.List;

/**
 * 菜品管理(Dish)表服务接口
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
public interface DishService extends IService<Dish> {
    void saveFlavor(DishDTO dishDTO);

    void deleteByIds(List<Long> ids);

    void updateFlavor(DishDTO dishDTO);
}

