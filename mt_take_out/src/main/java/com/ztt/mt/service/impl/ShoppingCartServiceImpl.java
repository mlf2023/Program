package com.ztt.mt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import  com.ztt.mt.service.ShoppingCartService;
import com.ztt.mt.mapper.ShoppingCartMapper;
import com.ztt.mt.pojo.ShoppingCart;
import com.ztt.mt.utils.BaseContext;
import com.ztt.mt.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 购物车(ShoppingCart)表服务实现类
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@Transactional
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        LambdaQueryWrapper<ShoppingCart> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        Long dishId=shoppingCart.getDishId();
        if(dishId!=null){
            wrapper.eq(ShoppingCart::getDishId,dishId);
        }
        else{
            wrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }
        ShoppingCart cart=this.getOne(wrapper);
        if(cart!=null){
            cart.setNumber(cart.getNumber()+1);
            this.updateById(cart);
        }
        else{
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCart.setNumber(1);
            shoppingCart.setUserId(BaseContext.getCurrentId());
            this.save(shoppingCart);
            cart=shoppingCart;
        }
        return cart;
    }

    @Override
    public ShoppingCart sub(ShoppingCart cart) {
        LambdaQueryWrapper<ShoppingCart> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId());
        if(cart.getDishId()!=null){
            wrapper.eq(ShoppingCart::getDishId,cart.getDishId());
        }
        else {
            wrapper.eq(ShoppingCart::getSetmealId,cart.getSetmealId());
        }
        ShoppingCart cart1=this.getOne(wrapper);
        if(cart1.getNumber()==1){
            this.removeById(cart1);
            cart1.setNumber(0);
            return cart1;
        }else {
            cart1.setNumber(cart1.getNumber()-1);
            this.updateById(cart1);
            return cart1;
        }
    }
}

