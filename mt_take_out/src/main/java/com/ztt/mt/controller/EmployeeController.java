package com.ztt.mt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ztt.mt.pojo.Employee;
import com.ztt.mt.service.EmployeeService;
import com.ztt.mt.utils.R;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * 员工信息(Employee)表控制层
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    /**
     * 服务对象
     */
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping("/login")
    public R<Employee> login(HttpSession session, @RequestBody Employee employee) {
        String password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        System.out.println(password);
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(wrapper);
        if (emp == null) {
            return R.error("用户名不存在！");
        }
        if(!password.equals(emp.getPassword())){
            return R.error("密码错误！");
        }
        if(emp.getStatus()==0){
            return R.error("用户已禁用！");
        }
        session.setAttribute("employee",emp.getId());
        return R.success(emp);

    }

    @PostMapping("/logout")
    public R<String> logout(HttpSession session){
        session.removeAttribute("employee");
        return R.success("退出！");
    }

   @PostMapping
    public  R<String> addEmployee(HttpSession session,@RequestBody Employee employee){
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        Long userId =(Long) session.getAttribute("employee");
        employee.setCreateUser(userId);
        employee.setUpdateUser(userId);
        employeeService.save(employee);
        return R.success("添加成功");
   }
   @PutMapping
   public R<String> updateStatus(HttpSession session,@RequestBody Employee employee){
        Long userId=(Long) session.getAttribute("employee");
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(userId);
        employeeService.updateById(employee);
        return  R.success("修改状态成功");
   }
    @GetMapping("/page")
    public  R<IPage<Employee>> page(@RequestParam Integer page,
                                    @RequestParam Integer pageSize,
                                    @RequestParam(required = false) String name){
    IPage<Employee> ipage=new Page<>(page,pageSize);
    LambdaQueryWrapper<Employee> wrapper=new LambdaQueryWrapper<>();
    wrapper.like(!StringUtils.isEmpty(name),Employee::getName,name).orderByDesc(Employee::getUpdateTime);
    employeeService.page(ipage,wrapper);
    return R.success(ipage);
}
    @GetMapping("/{id}")
    public R<Employee> getEmployeeById(@PathVariable("id") Long userId){
        Employee employee=employeeService.getById(userId);
        return R.success(employee);
    }
}

