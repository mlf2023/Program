package com.ztt.mt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ztt.mt.pojo.ShoppingCart;
import com.ztt.mt.service.ShoppingCartService;
import com.ztt.mt.utils.BaseContext;
import com.ztt.mt.utils.R;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车(ShoppingCart)表控制层
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {
    /**
     * 服务对象
     */
    @Autowired
    private ShoppingCartService shoppingCartService;
    @GetMapping("/list")
    public R<List<ShoppingCart>> shoppingCarts(){
        List<ShoppingCart> shoppingCarts=shoppingCartService.list();
        return R.success(shoppingCarts);
    }
@PostMapping("/add")
    public R<ShoppingCart>add(@RequestBody ShoppingCart shoppingCart){
    ShoppingCart cart = shoppingCartService.add(shoppingCart);
    return R.success(cart);
}
@PostMapping("/sub")
    public R<ShoppingCart> sub(@RequestBody ShoppingCart cart){
        ShoppingCart cart1=shoppingCartService.sub(cart);
        return R.success(cart1);
}
@DeleteMapping("/clean")
    public R<String> deleteShop(){
    LambdaQueryWrapper<ShoppingCart> wrapper=new LambdaQueryWrapper<>();
    wrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
    shoppingCartService.remove(wrapper);
    return R.success("清空完成！");
}
}

