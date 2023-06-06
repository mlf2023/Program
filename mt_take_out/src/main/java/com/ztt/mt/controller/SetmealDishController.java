package com.ztt.mt.controller;

import com.ztt.mt.pojo.SetmealDish;
import com.ztt.mt.service.SetmealDishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * 套餐菜品关系(SetmealDish)表控制层
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@RestController
@RequestMapping("setmealDish")
public class SetmealDishController {
    /**
     * 服务对象
     */
    @Autowired
    private SetmealDishService setmealDishService;


}

