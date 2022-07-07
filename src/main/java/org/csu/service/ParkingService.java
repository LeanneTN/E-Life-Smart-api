package org.csu.service;

import org.csu.domain.Car;
import org.csu.domain.ParkingSpace;
import org.csu.vo.ResponseResult;

public interface ParkingService {
    ResponseResult park(String id);

    ResponseResult leave(String id);

    ResponseResult getLog();

    ResponseResult getLogByCarNum(String carNum);

    ResponseResult getCarInfo();

    ResponseResult getParkingSpaceInfo();

    ResponseResult getInfoByUserid(String userid);

    ResponseResult addCar(Car car);

    ResponseResult addParkingSpace(ParkingSpace parkingSpace);
}
