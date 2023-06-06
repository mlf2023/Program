package com.ztt.mt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ztt.mt.pojo.AddressBook;
import com.ztt.mt.service.AddressBookService;
import com.ztt.mt.utils.BaseContext;
import com.ztt.mt.utils.R;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 地址管理(AddressBook)表控制层
 *
 * @author makejava
 * @since 2023-05-29 11:08:25
 */
@RestController
@RequestMapping("addressBook")
public class AddressBookController {
    /**
     * 服务对象
     */
    @Autowired
    private AddressBookService addressBookService;
    @GetMapping("list")
    public R<List<AddressBook>> listAdd(HttpSession session){
        Long id = BaseContext.getCurrentId();
        LambdaQueryWrapper<AddressBook> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(AddressBook::getUserId,id);
        List<AddressBook> addressBooks = addressBookService.list(wrapper);
        return R.success(addressBooks);
    }
    @PostMapping
    public R<String> add(@RequestBody AddressBook addressBook){
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBookService.save(addressBook);
        return R.success("地址保存成功");
    }
@PutMapping("/default")
    public R<String> defaultAddress(@RequestBody AddressBook addressBook){
        addressBookService.defaultAddress(addressBook);
        return R.success("设置默认地址成功");
}
    @GetMapping("/{id}")
    public R<AddressBook> getAdd(@PathVariable("id") Long id)
    {
       return R.success(addressBookService.getById(id));
    }
    @PutMapping
    public R<String> updataAdd(@RequestBody AddressBook addressBook){
        addressBookService.updateById(addressBook);
        return R.success("修改成功！");
    }
    @DeleteMapping
    public R<String> deleteAdd(Long ids){
        addressBookService.removeById(ids);
        return R.success("删除成功！");
    }
    @GetMapping("/default")
    public R<AddressBook> getAdd(){
        LambdaQueryWrapper<AddressBook> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(AddressBook::getUserId,BaseContext.getCurrentId()).eq(AddressBook::getIsDefault,1);
        AddressBook addressBook = addressBookService.getOne(wrapper);
        if(addressBook==null){
            return R.error("获取默认地址失败");
        }else {
            return R.success(addressBook);
        }

    }
}

