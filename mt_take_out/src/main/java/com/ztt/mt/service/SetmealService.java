package com.ztt.mt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ztt.mt.dto.SetmealDTO;
import com.ztt.mt.pojo.Setmeal;

import java.util.List;

/**
 * 套餐(Setmeal)表服务接口
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
public interface SetmealService extends IService<Setmeal> {
    void saveSetmeal(SetmealDTO setmealDTO);

    void deleteSetmeals(List<Long> ids);

    void updateSetDto(SetmealDTO setmealDTO);
}

