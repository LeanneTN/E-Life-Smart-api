package org.csu.controller;

import org.csu.vo.ResponseResult;
import org.csu.domain.User;
import org.csu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    @Autowired
    private UserService userService;

    //检查用户名是否可用
    @GetMapping("/username/{username}")
    public ResponseResult isUserNameExist(@PathVariable("username") String userName){
        return userService.isUserNameExist(userName);
    }

    //注册
    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }

    //登录
    @PostMapping("/login_by_account")
    public ResponseResult loginByAccount(@RequestBody User user){
        return userService.loginByAccount(user);
    }

    //获取图片验证码
    @GetMapping("/captcha")
    public ResponseResult getCaptcha(HttpServletRequest req, HttpServletResponse res) throws Exception{
        return userService.getCaptcha(req, res);
    }

    //注销
    @RequestMapping("/logout")
    public ResponseResult logout(){
        return userService.logout();
    }

    //获取已登录账号的信息
    @GetMapping("/get_login_user")
    public ResponseResult getLoginUser(HttpServletRequest req){
        return userService.getLoginUser(req);
    }

    //发送手机验证码
    @PostMapping("/phone_code")
    public ResponseResult getPhoneCode(@RequestParam("phoneNumber") String phoneNumber){
        return userService.getPhoneCode(phoneNumber);
    }

    //检查手机号是否可用
    @GetMapping("/phone/{number}")
    public ResponseResult isPhoneNumberExist(@PathVariable("number") String number){
        return userService.isPhoneNumberExist(number);
    }

    //为账号绑定手机号
    @PostMapping("/phone/{number}")
    public ResponseResult bindPhoneNumber(@PathVariable("number") String number,
                                          HttpServletRequest req){
        return userService.bindPhoneNumber(number, req);
    }

    //手机号登录
    @PostMapping("/login_by_phone")
    public ResponseResult loginByPhone(@RequestParam("phone") String phone){
        return userService.loginByPhone(phone);
    }

    @GetMapping("/{userid}")
    public ResponseResult getUserInfoById(@PathVariable("userid") String userid) {
        return userService.getUserInfoById(userid);
    }

    @PostMapping("/user_info")
    public ResponseResult updateUserInfoById(@RequestBody User user) {
        return userService.updateUserInfoById(user);
    }
}
