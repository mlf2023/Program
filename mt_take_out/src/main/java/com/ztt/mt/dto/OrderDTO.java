package com.ztt.mt.dto;

import com.ztt.mt.pojo.OrderDetail;
import com.ztt.mt.pojo.Orders;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO extends Orders {
    private List<OrderDetail> orderDetails;
    private String userName;
}
