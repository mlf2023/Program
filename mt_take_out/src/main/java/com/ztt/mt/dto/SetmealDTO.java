package com.ztt.mt.dto;

import com.ztt.mt.pojo.Setmeal;
import com.ztt.mt.pojo.SetmealDish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class SetmealDTO extends Setmeal {
    private String categoryName;
    private List<SetmealDish> setmealDishes=new ArrayList<>();

}
