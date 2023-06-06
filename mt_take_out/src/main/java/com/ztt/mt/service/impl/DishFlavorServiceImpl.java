package com.ztt.mt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import  com.ztt.mt.service.DishFlavorService;
import com.ztt.mt.mapper.DishFlavorMapper;
import com.ztt.mt.pojo.DishFlavor;
import org.springframework.stereotype.Service;

/**
 * 菜品口味关系表(DishFlavor)表服务实现类
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}

