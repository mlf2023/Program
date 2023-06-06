package com.ztt.mt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ztt.mt.pojo.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜品口味关系表(DishFlavor)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {
}

