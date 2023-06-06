package com.ztt.mt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import  com.ztt.mt.service.UserService;
import com.ztt.mt.mapper.UserMapper;
import com.ztt.mt.pojo.User;
import org.springframework.stereotype.Service;

/**
 * 用户信息(User)表服务实现类
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}

