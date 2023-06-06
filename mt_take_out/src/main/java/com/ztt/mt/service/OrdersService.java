package com.ztt.mt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ztt.mt.pojo.Orders;

/**
 * 订单表(Orders)表服务接口
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
public interface OrdersService extends IService<Orders> {
    Orders addOrders(Orders orders);
}

