package com.ztt.mt.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ztt.mt.dto.DishDTO;
import com.ztt.mt.dto.SetmealDTO;
import com.ztt.mt.pojo.Category;
import com.ztt.mt.pojo.Dish;
import com.ztt.mt.pojo.Setmeal;
import com.ztt.mt.pojo.SetmealDish;
import com.ztt.mt.service.CategoryService;
import com.ztt.mt.service.DishService;
import com.ztt.mt.service.SetmealDishService;
import com.ztt.mt.service.SetmealService;
import com.ztt.mt.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 套餐(Setmeal)表控制层
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@RestController
@RequestMapping("setmeal")
public class SetmealController {
    /**
     * 服务对象
     */
    @Autowired
    DishService dishService;
    @Autowired
    private SetmealService setmealService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SetmealDishService setmealDishService;
    @PostMapping
    public R<String> saveSetmeal(@RequestBody SetmealDTO setmealDTO){
        setmealService.saveSetmeal(setmealDTO);
        return R.success("保存套餐成功！");
    }
    @GetMapping("/page")
    public R<IPage<SetmealDTO>> pageSetmeal(@RequestParam Integer page,
                                         @RequestParam Integer pageSize,
                                         @RequestParam(required = false) String name){
        IPage<SetmealDTO> setmealDTOIPage=new Page<>();
        IPage<Setmeal> setmealIPage =new Page<>(page,pageSize);
        LambdaQueryWrapper<Setmeal> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(!StringUtils.isEmpty(name),Setmeal::getName, name).orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(setmealIPage,wrapper);
        BeanUtils.copyProperties(setmealIPage,setmealDTOIPage,"records");
        List<SetmealDTO> setmealDTOS=setmealIPage.getRecords().stream().map(setmeal -> {
            SetmealDTO setmealDTO=new SetmealDTO();
            BeanUtils.copyProperties(setmeal,setmealDTO);
            setmealDTO.setCategoryName(categoryService.getById(setmealDTO.getCategoryId()).getName());
            return setmealDTO;
        }).collect(Collectors.toList());
        setmealDTOIPage.setRecords(setmealDTOS);
        return R.success(setmealDTOIPage);

    }
    @PostMapping("status/{status}")
    public  R<String> status(@PathVariable("status")Integer status,@RequestParam List<Long> ids){
        LambdaUpdateWrapper<Setmeal> wrapper=new LambdaUpdateWrapper<>();
        wrapper.in(Setmeal::getId,ids).set(Setmeal::getStatus,status).set(Setmeal::getUpdateTime,LocalDateTime.now());
        setmealService.update(wrapper);
        return R.success("修改套餐状态成功");
    }
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        setmealService.deleteSetmeals(ids);
        return R.success("删除成功");
    }
    @GetMapping("/{id}")
    public R<SetmealDTO> getSetmealById(@PathVariable("id") Long id) {
        Setmeal setmeal = setmealService.getById(id);
        SetmealDTO setmealDTO=new SetmealDTO();
        BeanUtils.copyProperties(setmeal,setmealDTO);
        LambdaQueryWrapper<SetmealDish> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(SetmealDish::getSetmealId,setmealDTO.getId());
        List<SetmealDish> setmealDishs=setmealDishService.list(wrapper);
        setmealDTO.setSetmealDishes(setmealDishs);
        return R.success(setmealDTO);
    }
    @GetMapping("/list")
    public R<List<Setmeal>> listSetmeal(Setmeal setmeal){
        LambdaQueryWrapper<Setmeal> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Setmeal::getCategoryId,setmeal.getCategoryId()).orderByDesc(Setmeal::getUpdateTime).eq(setmeal.getStatus()!=null,Setmeal::getStatus,setmeal.getStatus());
        List<Setmeal> setmeals=setmealService.list(wrapper);
        return R.success(setmeals);
    }
    @GetMapping("/dish/{id}")
    public R<List<DishDTO>> getDish(@PathVariable Long id){
        LambdaQueryWrapper<SetmealDish> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(SetmealDish::getSetmealId,id).orderByDesc(SetmealDish::getUpdateTime);
        List<SetmealDish>setmealDishes= setmealDishService.list(wrapper);
        List<DishDTO> dishDTOS=setmealDishes.stream().map(setmealDish -> {
            DishDTO dishDTO=new DishDTO();
            dishDTO.setCopies(setmealDish.getCopies());
            LambdaQueryWrapper<Dish> wrapper1=new LambdaQueryWrapper<>();
            wrapper1.eq(Dish::getId,setmealDish.getDishId());
            Dish dish=dishService.getOne(wrapper1);
            BeanUtils.copyProperties(dish,dishDTO);
            return dishDTO;
        }).collect(Collectors.toList());
        return R.success(dishDTOS);
    }
    @PutMapping
    public R<String> updateSetmeal(@RequestBody SetmealDTO setmealDTO){
        setmealService.updateSetDto(setmealDTO);
        return R.success("修改成功！");
    }
}

