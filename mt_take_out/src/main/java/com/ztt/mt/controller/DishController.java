package com.ztt.mt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ztt.mt.dto.DishDTO;
import com.ztt.mt.pojo.Category;
import com.ztt.mt.pojo.Dish;
import com.ztt.mt.pojo.DishFlavor;
import com.ztt.mt.service.CategoryService;
import com.ztt.mt.service.DishFlavorService;
import com.ztt.mt.service.DishService;
import com.ztt.mt.utils.BaseContext;
import com.ztt.mt.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品管理(Dish)表控制层
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@RestController
@RequestMapping("dish")
public class DishController {
    /**
     * 服务对象
     */
    @Autowired
    private DishService dishService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    DishFlavorService dishFlavorService;
    @PostMapping
    public R<String> saveDishFlover(@RequestBody DishDTO dishDTO){
        dishService.saveFlavor(dishDTO);
        return R.success("菜品保存成功！");
    }
    @GetMapping("/page")
    public R<IPage<DishDTO>> page(@RequestParam Integer page,
                                   @RequestParam Integer pageSize,
    @RequestParam(required = false) String name
    ){  IPage<DishDTO> iPageDTO=new Page<>(page,pageSize);
        IPage<Dish> iPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Dish> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(!StringUtils.isEmpty(name),Dish::getName,name).orderByDesc(Dish::getUpdateTime);
        dishService.page(iPage,wrapper);
        BeanUtils.copyProperties(iPage,iPageDTO,"records");
        List<Dish> dishes = iPage.getRecords();
        List<DishDTO> dishDTOList=dishes.stream().map(dish -> {
            DishDTO dishDTO=new DishDTO();
            BeanUtils.copyProperties(dish,dishDTO);
            Category category=categoryService.getById(dish.getCategoryId());
            dishDTO.setCategoryName(category.getName());
            return dishDTO;
        }).collect(Collectors.toList());
        iPageDTO.setRecords(dishDTOList);
        return R.success(iPageDTO);
    }
    @PostMapping("/status/{statu}")
    public R<String> status(@PathVariable("statu") int status,@RequestParam List<Long> ids){
        LambdaUpdateWrapper<Dish> wrapper=new LambdaUpdateWrapper<>();
        wrapper.set(Dish::getStatus,status).in(Dish::getId,ids).set(Dish::getUpdateTime, LocalDateTime.now());
        dishService.update(wrapper);
        return R.success("修改成功！");
    }
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        dishService.deleteByIds(ids);
        return R.success("删除成功！");
    }
    @GetMapping("/{dishId}")
    public  R<DishDTO> getDishFlavorById(@PathVariable("dishId") Long dishId){
        DishDTO dishDTO=new DishDTO();
        Dish dish=dishService.getById(dishId);
        BeanUtils.copyProperties(dish,dishDTO);
        LambdaQueryWrapper<DishFlavor>  wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(DishFlavor::getDishId,dishId);
        List<DishFlavor> dishFlavors=dishFlavorService.list(wrapper);
        dishDTO.setFlavors(dishFlavors);
        return R.success(dishDTO);
    }
    @PutMapping
    public R<String> updataDishFlover(@RequestBody DishDTO dishDTO){
        dishService.updateFlavor(dishDTO);
        return R.success("菜品修改成功！");
    }
    @GetMapping("/list")
    public R<List<DishDTO>> list(Dish dish){
        LambdaQueryWrapper<Dish> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Dish::getCategoryId,dish.getCategoryId()).eq(dish.getStatus()!=null,Dish::getStatus,dish.getStatus()).orderByDesc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        List<Dish> dishes=dishService.list(wrapper);
        List<DishDTO> dishDTOS=dishes.stream().map(dish1 -> {
            DishDTO dishDTO=new DishDTO();
            BeanUtils.copyProperties(dish1,dishDTO);
            LambdaQueryWrapper<DishFlavor> wrapper1=new LambdaQueryWrapper<>();
            wrapper1.eq(DishFlavor::getDishId,dish1.getId());
            dishFlavorService.list(wrapper1);
            List<DishFlavor> flavors=dishFlavorService.list(wrapper1);
            dishDTO.setFlavors(flavors);
            return dishDTO;
        }).collect(Collectors.toList());
        return R.success(dishDTOS);
    }
//    @GetMapping("/list")
//    public R<List<Dish>> list(@RequestParam Long categoryId){
//        LambdaQueryWrapper<Dish> wrapper=new LambdaQueryWrapper<>();
//        wrapper.eq(Dish::getCategoryId,categoryId).orderByDesc(Dish::getUpdateTime).eq(Dish::getStatus,1);
//        List<Dish> dishes = dishService.list(wrapper);
//        return R.success(dishes);
//    }

}

