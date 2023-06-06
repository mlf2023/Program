package com.ztt.mt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ztt.mt.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工信息(Employee)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}

