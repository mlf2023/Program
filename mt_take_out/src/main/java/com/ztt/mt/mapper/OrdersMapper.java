package com.ztt.mt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ztt.mt.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表(Orders)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}

