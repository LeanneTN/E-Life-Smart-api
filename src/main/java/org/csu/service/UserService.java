package org.csu.service;

import org.csu.vo.ResponseResult;
import org.csu.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    ResponseResult loginByAccount(User user);

    ResponseResult loginByPhone(String phone);

    ResponseResult logout();

    ResponseResult getPhoneCode(String phoneNumber);

    ResponseResult getCaptcha(HttpServletRequest req, HttpServletResponse res) throws Exception;

    ResponseResult getUserInfoById(Long id);

    ResponseResult updateUserInfoById(User user);

    ResponseResult register(User user);

    ResponseResult isUserNameExist(String userName);

    ResponseResult getLoginUser(HttpServletRequest req);

    ResponseResult isPhoneNumberExist(String number);

    ResponseResult bindPhoneNumber(String number, HttpServletRequest req);

    ResponseResult resetPassword(String oldPwd,String newPwd, HttpServletRequest req);

    ResponseResult getAllUserInfo();

    ResponseResult deleteUserById(long id);

    ResponseResult createUser(User user);

}
