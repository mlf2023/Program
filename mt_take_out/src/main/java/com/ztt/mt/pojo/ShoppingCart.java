package com.ztt.mt.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * 购物车(ShoppingCart)表实体类
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@Data
@NoArgsConstructor
@TableName("shopping_cart")
public class ShoppingCart implements Serializable {
    
        /**
    * 主键
    */
            @TableId(value = "id", type = IdType.AUTO)
        private Long id;

        /**
    * 名称
    */
            private String name;

        /**
    * 图片
    */
            private String image;

        /**
    * 主键
    */
            private Long userId;

        /**
    * 菜品id
    */
            private Long dishId;

        /**
    * 套餐id
    */
            private Long setmealId;

        /**
    * 口味
    */
            private String dishFlavor;

        /**
    * 数量
    */
            private Integer number;

        /**
    * 金额
    */
            private BigDecimal amount;

        /**
    * 创建时间
    */
            private LocalDateTime createTime;

}

