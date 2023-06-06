package com.ztt.mt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ztt.mt.email.SendEmailUtils;
import com.ztt.mt.email.ValidateCodeUtils;
import com.ztt.mt.pojo.User;
import com.ztt.mt.service.UserService;
import com.ztt.mt.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 用户信息(User)表控制层
 *
 * @author makejava
 * @since 2023-05-29 11:08:28
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;
    @PostMapping("/sendMsg")
    public R<String> sendMsg(HttpSession session, @RequestBody Map<String,String> email){
        String code=ValidateCodeUtils.generateValidateCode4String(6);
        log.info("登录验证码：",code);
        try {
            SendEmailUtils.sendAuthCodeEmail(email.get("phone"), code);
            session.setAttribute("code",code);
            return R.success("已发送");
        } catch (EmailException e) {
            e.printStackTrace();
            return R.error("发送失败！");
        }
    }
    @PostMapping("/login")
    public R<String> login(HttpSession session,@RequestBody Map<String,Object> map){
        String phone = (String) map.get("phone");
        String code= (String) map.get("code");
        String sessionCode= (String) session.getAttribute("code");
        if(code!=null&& code.equals(sessionCode)){
            LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(User::getPhone,phone);
            User user=userService.getOne(wrapper);
            if(user==null){
                user=new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());
            return R.success("用户登录成功！");
        }else
        return R.error("验证码验证失败！");
    }
}

