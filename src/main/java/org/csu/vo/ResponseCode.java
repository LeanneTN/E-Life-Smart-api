package org.csu.vo;

import lombok.Getter;
//注意：枚举属性间使用’,‘隔开，最后使用’;‘结尾
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
    NO_CAR_LOG(486,"NO_CAR_LOG"),//用户名下没有车
    PARKED(486, "PARKED"),//停过了
    PAYMENT_EXIST(456, "PAYMENT_EXIST"),//订单被支付过了
    USER_HEALTH_CHECK_SUBMIT_FAIL(451, "USER_HEALTH_CHECK_SUBMIT_FAIL"),//健康打卡提交信息失败
    USER_HEALTH_CHECK_NOT_EXIST(450, "USER_HEALTH_CHECK_NOT_EXIST"),//找不到该健康打卡信息

    NO_TOPIC_LOG(430,"NO_TOPIC_LOG"),//没有查询话题记录
    NO_COMMENT_LOG(431,"NO_COMMENT_LOG"),//没有查询到回帖记录


    USER_VOLUNTEER_INFO_INCOMPLETE(440, "USER_VOLUNTEER_INFO_INCOMPLETE"),//志愿者信息不完善
    USER_VOLUNTEER_LOG_EMPTY(441, "USER_VOLUNTEER_LOG_EMPTY"),//志愿者日志为空
    NOT_VOLUNTEER(442, "NOT_VOLUNTEER"),//不是志愿者

    ACID_NOT_GET(443, "ACID_NOT_GET"); //核酸检测结果未取到

    private final int code;
    private final String description;

    ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
