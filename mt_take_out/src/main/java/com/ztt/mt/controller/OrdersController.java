package com.ztt.mt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ztt.mt.dto.OrderDTO;
import com.ztt.mt.pojo.OrderDetail;
import com.ztt.mt.pojo.Orders;
import com.ztt.mt.pojo.User;
import com.ztt.mt.service.OrderDetailService;
import com.ztt.mt.service.OrdersService;
import com.ztt.mt.service.UserService;
import com.ztt.mt.utils.BaseContext;
import com.ztt.mt.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单表(Orders)表控制层
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@Controller
@RequestMapping("order")
public class OrdersController {
    /**
     * 服务对象
     */
    @Autowired
    private OrdersService ordersService;
    @Autowired
    UserService userService;
    @Autowired
    private OrderDetailService orderDetailService;
@PostMapping("/submit")
@ResponseBody
    public R<Orders> submit(@RequestBody Orders orders){
    Orders order=ordersService.addOrders(orders);
       return R.success(order);
    }
    @GetMapping("/userPage")
    @ResponseBody
    public R<IPage<OrderDTO>> getOrder(@RequestParam Integer page, @RequestParam Integer pageSize){
        IPage<OrderDTO> orderDTOIPage=new Page<>(page,pageSize);
        IPage<Orders> ordersIPage=new Page<>(page,pageSize);
        LambdaQueryWrapper<Orders> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Orders::getUserId, BaseContext.getCurrentId()).orderByDesc(Orders::getOrderTime);
        ordersService.page(ordersIPage,wrapper);
        BeanUtils.copyProperties(ordersIPage,orderDTOIPage,"records");
        List<OrderDTO> orderDTOS = ordersIPage.getRecords().stream().map(orders -> {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orders, orderDTO);
            LambdaQueryWrapper<OrderDetail> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(OrderDetail::getOrderId, orderDTO.getNumber());
            List<OrderDetail> orderDetails = orderDetailService.list(wrapper1);
            orderDTO.setOrderDetails(orderDetails);
            return orderDTO;
        }).collect(Collectors.toList());
        orderDTOIPage.setRecords(orderDTOS);
        return R.success(orderDTOIPage);

    }
    @GetMapping("/afterPayOrder")
    public String afterOrder(@RequestParam String out_trade_no){
    LambdaUpdateWrapper<Orders> wrapper=new LambdaUpdateWrapper<>();
    wrapper.eq(Orders::getNumber,out_trade_no).set(Orders::getStatus,2).set(Orders::getCheckoutTime, LocalDateTime.now());
    ordersService.update(wrapper);
    return "redirect:/front/page/pay-success.html";
    }
    @GetMapping("/page")
    @ResponseBody
    public R<IPage<OrderDTO>> getOrders(@RequestParam Integer page, @RequestParam Integer pageSize,@RequestParam(required = false) LocalDateTime beginTime,@RequestParam(required = false) LocalDateTime endTime,@RequestParam(required = false) Long number){
        IPage<OrderDTO> orderDTOIPage=new Page<>(page,pageSize);
        IPage<Orders> ordersIPage=new Page<>(page,pageSize);
        LambdaQueryWrapper<Orders> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Orders::getUserId, BaseContext.getCurrentId()).like(number!=null,Orders::getNumber,number).le(endTime!=null,Orders::getCheckoutTime,endTime).ge(beginTime!=null,Orders::getOrderTime,beginTime).orderByDesc(Orders::getOrderTime);
        ordersService.page(ordersIPage,wrapper);
        BeanUtils.copyProperties(ordersIPage,orderDTOIPage,"records");
        List<OrderDTO> orderDTOS = ordersIPage.getRecords().stream().map(orders -> {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orders, orderDTO);
            User user=userService.getById(orders.getUserId());
            orderDTO.setUserName(user.getName());
            return orderDTO;
        }).collect(Collectors.toList());
        orderDTOIPage.setRecords(orderDTOS);
        return R.success(orderDTOIPage);

    }
}

