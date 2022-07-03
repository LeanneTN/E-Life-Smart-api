package com.sangeng.controller;

import com.sangeng.vo.ResponseResult;
import com.sangeng.domain.User;
import com.sangeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

    //检查用户名是否可用
    @GetMapping("/username/{username}")
    public ResponseResult isUserNameExist(@PathVariable("username") String userName){
        return userService.isUserNameExist(userName);
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login_by_account")
    public ResponseResult loginByAccount(@RequestBody User user){
        return userService.loginByAccount(user);
    }

    @PostMapping("/login_by_phone")
    public ResponseResult loginByPhone(@RequestBody User user){
        return userService.loginByPhone(user);
    }

    @PostMapping("/phone_code")
    public ResponseResult getPhoneCode(){
        return userService.getPhoneCode();
    }

    @PostMapping("/captcha")
    public ResponseResult getCaptcha(){
        return userService.getCaptcha();
    }

    @GetMapping("/{userid}")
    public ResponseResult getUserInfoById(@PathVariable("userid") String userid) {
        return userService.getUserInfoById(userid);
    }

    @PostMapping("/user_info")
    public ResponseResult updateUserInfoById(@RequestBody User user) {
        return userService.updateUserInfoById(user);
    }

    @RequestMapping("/logout")
    public ResponseResult logout(){
        return userService.logout();
    }
}
