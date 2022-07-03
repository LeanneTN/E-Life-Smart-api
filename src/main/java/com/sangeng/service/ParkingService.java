package com.sangeng.service;

import com.sangeng.vo.ResponseResult;

public interface ParkingService {
    ResponseResult park();

    ResponseResult leave();

    ResponseResult getLog();

    ResponseResult getLogByCarNum(String carNum);

    ResponseResult getInfo();

    ResponseResult getInfoByUserid(String userid);
}
