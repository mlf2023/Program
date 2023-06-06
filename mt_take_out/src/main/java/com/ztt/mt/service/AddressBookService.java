package com.ztt.mt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ztt.mt.pojo.AddressBook;

/**
 * 地址管理(AddressBook)表服务接口
 *
 * @author makejava
 * @since 2023-05-29 11:08:27
 */
public interface AddressBookService extends IService<AddressBook> {
    void defaultAddress(AddressBook addressBook);
}

