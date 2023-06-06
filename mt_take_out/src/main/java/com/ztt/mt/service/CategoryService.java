package com.ztt.mt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ztt.mt.pojo.Category;
import org.springframework.transaction.annotation.Transactional;

/**
 * 菜品及套餐分类(Category)表服务接口
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
public interface CategoryService extends IService<Category> {
    void removeByCateId(Long cateId);
}

