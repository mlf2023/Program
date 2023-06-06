package com.ztt.mt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import  com.ztt.mt.service.EmployeeService;
import com.ztt.mt.mapper.EmployeeMapper;
import com.ztt.mt.pojo.Employee;
import org.springframework.stereotype.Service;

/**
 * 员工信息(Employee)表服务实现类
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}

