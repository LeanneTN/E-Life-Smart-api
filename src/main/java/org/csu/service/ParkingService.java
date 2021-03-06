package org.csu.service;

import org.csu.domain.Car;
import org.csu.domain.Parking;
import org.csu.domain.ParkingSpace;
import org.csu.vo.ResponseResult;

public interface ParkingService {
    ResponseResult park(String id);

    ResponseResult leave(String id);

    ResponseResult getLog();

    ResponseResult getLogByCarNum(String carNum);

    ResponseResult updateParkInfoById(Parking parking);

    ResponseResult deleteParkById(String id);

    ResponseResult getCarInfo();

    ResponseResult getParkingSpaceInfo();

    ResponseResult getInfoByUserid(String userid);

    ResponseResult addCar(Car car);

    ResponseResult updateCarInfoById(Car car);

    ResponseResult deleteCarById(String id);

    ResponseResult addParkingSpace(ParkingSpace parkingSpace);

    ResponseResult updateParkSpaceInfoById(ParkingSpace parkingSpace);

    ResponseResult deleteParkSpaceById(String id);

    ResponseResult ownCar(long uid);
}
