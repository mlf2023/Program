package com.ztt.mt.controller;

import com.ztt.mt.pojo.DishFlavor;
import com.ztt.mt.service.DishFlavorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * 菜品口味关系表(DishFlavor)表控制层
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@RestController
@RequestMapping("dishFlavor")
public class DishFlavorController {
    /**
     * 服务对象
     */
    @Autowired
    private DishFlavorService dishFlavorService;


}

