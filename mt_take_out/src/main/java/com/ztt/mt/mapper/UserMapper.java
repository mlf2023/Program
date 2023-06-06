package com.ztt.mt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ztt.mt.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息(User)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

