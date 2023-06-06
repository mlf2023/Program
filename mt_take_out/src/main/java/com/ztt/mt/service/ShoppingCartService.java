package com.ztt.mt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ztt.mt.pojo.ShoppingCart;
import com.ztt.mt.utils.R;

/**
 * 购物车(ShoppingCart)表服务接口
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
public interface ShoppingCartService extends IService<ShoppingCart> {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart sub(ShoppingCart cart);
}

