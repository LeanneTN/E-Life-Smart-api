package org.csu.vo;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS(200, "SUCCESS"),//成功
    ERROR(500, "ERROR"),//服务器错误
    USERNAME_EXIST(499, "USERNAME_EXIST"),//用户名被占用
    NOT_LOGIN(498, "NOT_LOGIN"),//未登录
    CODE_ERROR(497, "CODE_ERROR"),//获取验证码失败
    PHONE_EXIST(496, "PHONE_EXIST"),//手机号被占用
    ACCOUNT_NOT_EXIST(495, "ACCOUNT_NOT_EXIST"),//账号不存在
    CAR_EXIST(489, "CAR_EXIST"),//车辆在系统中已经存在
    PARKING_SPACE_FULL(488, "PARKING_SPACE_FULL"),//车位被占满了
    NO_PARKING_LOG(487, "NO_PARKING_LOG"),//未查询到停车记录
    PARKED(486, "PARKED"),//停过了
    USER_HEALTH_CHECK_SUBMIT_FAIL(451, "USER_HEALTH_CHECK_SUBMIT_FAIL"),//健康打卡提交信息失败
    USER_HEALTH_CHECK_NOT_EXIST(450, "USER_HEALTH_CHECK_NOT_EXIST"),//找不到该健康打卡信息
    USER_VOLUNTEER_INFO_INCOMPLETE(440, "USER_VOLUNTEER_INFO_INCOMPLETE"),
    USER_VOLUNTEER_LOG_EMPTY(441, "USER_VOLUNTEER_LOG_EMPTY");

    private final int code;
    private final String description;

    ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
