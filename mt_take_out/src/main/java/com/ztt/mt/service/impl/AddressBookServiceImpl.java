package com.ztt.mt.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import  com.ztt.mt.service.AddressBookService;
import com.ztt.mt.mapper.AddressBookMapper;
import com.ztt.mt.pojo.AddressBook;
import com.ztt.mt.utils.BaseContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 地址管理(AddressBook)表服务实现类
 *
 * @author makejava
 * @since 2023-05-29 11:08:27
 */
@Transactional
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

    @Override
    public void defaultAddress(AddressBook addressBook) {
        LambdaUpdateWrapper<AddressBook> wrapper1 =new LambdaUpdateWrapper<>();
        wrapper1.eq(AddressBook::getUserId, BaseContext.getCurrentId()).set(AddressBook::getIsDefault,0);
        this.update(wrapper1);
        LambdaUpdateWrapper<AddressBook> wrapper2=new LambdaUpdateWrapper<>();
        wrapper2.eq(AddressBook::getId, addressBook.getId()).set(AddressBook::getIsDefault,1);
        this.update(wrapper2);
    }
}

