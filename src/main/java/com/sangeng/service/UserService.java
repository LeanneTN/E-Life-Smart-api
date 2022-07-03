package com.sangeng.service;

import com.sangeng.vo.ResponseResult;
import com.sangeng.domain.User;

public interface UserService {
    ResponseResult loginByAccount(User user);

    ResponseResult loginByPhone(User user);

    ResponseResult logout();

    ResponseResult getPhoneCode();

    ResponseResult getCaptcha();

    ResponseResult getUserInfoById(String userid);

    ResponseResult updateUserInfoById(User user);

    ResponseResult register(User user);
}
