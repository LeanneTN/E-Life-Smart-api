package org.csu.service;

import org.csu.vo.ResponseResult;

public interface ParkingService {
    ResponseResult park();

    ResponseResult leave();

    ResponseResult getLog();

    ResponseResult getLogByCarNum(String carNum);

    ResponseResult getInfo();

    ResponseResult getInfoByUserid(String userid);
}
