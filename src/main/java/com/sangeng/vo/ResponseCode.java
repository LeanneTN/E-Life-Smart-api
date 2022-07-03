package com.sangeng.vo;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS(200, "SUCCESS"),//成功
    USERNAME_EXIST(499, "USERNAME_EXIST"),//用户名被占用
    ERROR(500, "ERROR");//服务器错误


    private final int code;
    private final String description;

    ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
