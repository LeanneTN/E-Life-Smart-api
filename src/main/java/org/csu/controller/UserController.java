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
    @GetMapping("/check_phone/{number}")
    public ResponseResult isPhoneNumberExist(@PathVariable("number") String number){
        return userService.isPhoneNumberExist(number);
    }

    //为账号绑定手机号
    @PostMapping("/bind_phone/{number}")
    public ResponseResult bindPhoneNumber(@PathVariable("number") String number,
                                          HttpServletRequest req){
        return userService.bindPhoneNumber(number, req);
    }

    //手机号登录
    @PostMapping("/login_by_phone")
    public ResponseResult loginByPhone(@RequestParam("phoneNumber") String phone){
        return userService.loginByPhone(phone);
    }

    //重置密码
    @PutMapping("/password")
    public ResponseResult resetPassword(@RequestParam("oldPwd") String oldPwd,
                                        @RequestParam("newPwd") String newPwd,
                                        HttpServletRequest req){
        return userService.resetPassword(oldPwd, newPwd, req);
    }

    //更新用户的信息
    @PostMapping("/user_info")
    public ResponseResult updateUserInfoById(@RequestBody User user) {
        return userService.updateUserInfoById(user);
    }

    @GetMapping("/{userid}")
    public ResponseResult getUserInfoById(@PathVariable("userid") String userid) {
        return userService.getUserInfoById(userid);
    }

    //获取所有用户的信息
    @GetMapping("/user_info")
    public ResponseResult getAllUserInfo() {
        return userService.getAllUserInfo();
    }

    //删除用户
    @DeleteMapping("/delete_user")
    public ResponseResult deleteUserById(@RequestParam("id") long id){ return userService.deleteUserById(id);}

    //新增用户
    @PostMapping("/new_user")
    public ResponseResult createUser(@RequestBody User user ){
        return this.userService.createUser(user);
    }
}
