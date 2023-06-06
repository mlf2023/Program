package com.ztt.mt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ztt.mt.pojo.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜品管理(Dish)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}

