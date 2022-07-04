package org.csu.vo;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS(200, "SUCCESS"),//成功
    USER_HEALTH_CHECK_NOT_EXIST(450, "USER_HEALTH_CHECK_NOT_EXIST"),
    PHONE_EXIST(496, "PHONE_EXIST"),//手机号被占用
    CODE_ERROR(497, "CODE_ERROR"),//获取验证码失败
    NOT_LOGIN(498, "NOT_LOGIN"),//未登录
    USERNAME_EXIST(499, "USERNAME_EXIST"),//用户名被占用
    ERROR(500, "ERROR");//服务器错误

    private final int code;
    private final String description;

    ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
