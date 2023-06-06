package com.ztt.mt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ztt.mt.common.CustomException;
import com.ztt.mt.pojo.*;
import com.ztt.mt.service.*;
import com.ztt.mt.mapper.OrdersMapper;
import com.ztt.mt.utils.BaseContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 订单表(Orders)表服务实现类
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@Transactional
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    @Autowired
    private AddressBookService addressBookService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    UserService userService;

    @Override
    public Orders addOrders(Orders orders) {
        Long id=IdWorker.getId();
        User user=userService.getById(BaseContext.getCurrentId());
        AddressBook addressBook=addressBookService.getById(orders.getAddressBookId());
        LambdaQueryWrapper<ShoppingCart> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        List<ShoppingCart> shoppingCarts=shoppingCartService.list(wrapper);
        if(CollectionUtils.isEmpty(shoppingCarts)){
            throw new CustomException("购物车为空");
        }
        AtomicInteger atomicInteger=new AtomicInteger();
        List<OrderDetail> orderDetails = shoppingCarts.stream().map(cart -> {
            atomicInteger.addAndGet(cart.getAmount().multiply(new BigDecimal(cart.getNumber())).intValue());
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(id);
            BeanUtils.copyProperties(cart, orderDetail);
            return orderDetail;
        }).collect(Collectors.toList());
        Orders order =new Orders();
        order.setStatus(1);
        order.setNumber(String.valueOf(id));
        order.setUserId(user.getId());
        order.setAddressBookId(orders.getAddressBookId());
        order.setOrderTime(LocalDateTime.now());
        order.setPayMethod(2);
        order.setAmount(new BigDecimal(atomicInteger.get()));
        order.setPhone(addressBook.getPhone());
        order.setAddress(addressBook.getDetail());
        order.setUserName(user.getName());
        order.setConsignee(addressBook.getConsignee());
        this.save(order);
        orderDetailService.saveBatch(orderDetails);
        LambdaUpdateWrapper<ShoppingCart> wrapper1=new LambdaUpdateWrapper<>();
        wrapper1.eq(ShoppingCart::getUserId,BaseContext.getCurrentId());
        shoppingCartService.remove(wrapper1);
        return order;
    }
}

