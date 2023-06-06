package com.ztt.mt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ztt.mt.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜品及套餐分类(Category)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}

