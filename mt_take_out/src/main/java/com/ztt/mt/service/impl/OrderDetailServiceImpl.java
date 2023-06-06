package com.ztt.mt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import  com.ztt.mt.service.OrderDetailService;
import com.ztt.mt.mapper.OrderDetailMapper;
import com.ztt.mt.pojo.OrderDetail;
import org.springframework.stereotype.Service;

/**
 * 订单明细表(OrderDetail)表服务实现类
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}

