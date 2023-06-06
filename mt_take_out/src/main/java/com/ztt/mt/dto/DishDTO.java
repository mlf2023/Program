package com.ztt.mt.dto;

import com.ztt.mt.pojo.Dish;
import com.ztt.mt.pojo.DishFlavor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDTO extends Dish {
    private List<DishFlavor> flavors=new ArrayList<>();
    private Integer copies;
    private String categoryName;
}
