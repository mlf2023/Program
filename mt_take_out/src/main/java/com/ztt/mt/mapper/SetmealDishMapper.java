package com.ztt.mt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ztt.mt.pojo.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

/**
 * 套餐菜品关系(SetmealDish)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@Mapper
public interface SetmealDishMapper extends BaseMapper<SetmealDish> {
}

