package com.ztt.mt.controller;

import com.ztt.mt.pojo.OrderDetail;
import com.ztt.mt.service.OrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * 订单明细表(OrderDetail)表控制层
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@RestController
@RequestMapping("orderDetail")
public class OrderDetailController {
    /**
     * 服务对象
     */
    @Autowired
    private OrderDetailService orderDetailService;


}

