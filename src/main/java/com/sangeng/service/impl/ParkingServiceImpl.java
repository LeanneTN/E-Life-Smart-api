package com.sangeng.service.impl;

import com.sangeng.vo.ResponseResult;
import com.sangeng.mapper.CarMapper;
import com.sangeng.mapper.ParkingMapper;
import com.sangeng.mapper.ParkingSpaceMapper;
import com.sangeng.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingServiceImpl implements ParkingService {
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private ParkingSpaceMapper parkingSpaceMapper;
    @Autowired
    private ParkingMapper parkingMapper;

    @Override
    public ResponseResult park() {
        return null;
    }

    @Override
    public ResponseResult leave() {
        return null;
    }

    @Override
    public ResponseResult getLog() {
        return null;
    }

    @Override
    public ResponseResult getLogByCarNum(String carNum) {
        return null;
    }

    @Override
    public ResponseResult getInfo() {
        return null;
    }

    @Override
    public ResponseResult getInfoByUserid(String userid) {
        return null;
    }
}
