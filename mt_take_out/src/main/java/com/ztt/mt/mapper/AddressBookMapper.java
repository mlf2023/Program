package com.ztt.mt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ztt.mt.pojo.AddressBook;
import org.apache.ibatis.annotations.Mapper;

/**
 * 地址管理(AddressBook)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-29 11:08:26
 */
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}

