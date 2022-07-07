package org.csu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.jdbc.Null;
import org.csu.domain.*;
import org.csu.mapper.PaymentMapper;
import org.csu.service.UserService;
import org.csu.vo.ResponseCode;
import org.csu.vo.ResponseResult;
import org.csu.mapper.CarMapper;
import org.csu.mapper.ParkingMapper;
import org.csu.mapper.ParkingSpaceMapper;
import org.csu.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ParkingServiceImpl implements ParkingService {
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private ParkingSpaceMapper parkingSpaceMapper;
    @Autowired
    private ParkingMapper parkingMapper;
    @Autowired
    private PaymentMapper paymentMapper;

    public static final double LIST_PRICE = 3.00;

    @Override
    public ResponseResult park(String id) {
        Parking parking = new Parking();
        //首先查询是否有空余的位置
        LambdaQueryWrapper<ParkingSpace> wrapper = new QueryWrapper<ParkingSpace>().lambda();
        wrapper.eq(ParkingSpace::getCarNum, "");
        List<ParkingSpace> parkingSpaces = parkingSpaceMapper.selectList(wrapper);
        int len = parkingSpaces.size();
        if(len == 0)
            return new ResponseResult(ResponseCode.PARKING_SPACE_FULL.getCode(), "车位已占满！");

        //再查这个车是否已经停过
        wrapper = new QueryWrapper<ParkingSpace>().lambda();
        wrapper.eq(ParkingSpace::getCarNum, id);
        ParkingSpace parkingSpace = parkingSpaceMapper.selectOne(wrapper);
        if(parkingSpace != null)
            return new ResponseResult(ResponseCode.PARKED.getCode(), "该车已经在车库中！");

        //随机分配空闲车位
        parking.setCarNum(id);
        Random random = new Random();
        int i = random.nextInt(len);
        parking.setParkingNum(parkingSpaces.get(i).getId());
        parkingSpaces.get(i).setCarNum(id);
        parkingSpaceMapper.updateById(parkingSpaces.get(i));

        //设置开始停车的时间
        parking.setStart(new Date());

        //最后查询是不是小区内的
        Car res = carMapper.selectById(id);
        if(res == null)
            parking.setRegistered(false);
        else
            parking.setRegistered(true);

        int insert = parkingMapper.insert(parking);
        if(insert > 0)
            return new ResponseResult(ResponseCode.SUCCESS.getCode(), "成功停车！");

        return new ResponseResult(ResponseCode.ERROR.getCode(), "服务器错误");
    }

    @Override
    public ResponseResult leave(String id) {
        //先查询有没有该车的停车记录
        LambdaQueryWrapper<ParkingSpace> parkingSpaceWrapper = new QueryWrapper<ParkingSpace>().lambda();
        parkingSpaceWrapper.eq(ParkingSpace::getCarNum, id);
        ParkingSpace parkingSpace = parkingSpaceMapper.selectOne(parkingSpaceWrapper);
        if(parkingSpace == null)
            return new ResponseResult(ResponseCode.NO_PARKING_LOG.getCode(), "未查询到该车有停车记录");

        LambdaQueryWrapper<Parking> parkingWrapper = new QueryWrapper<Parking>().lambda();
        parkingWrapper.eq(Parking::getCarNum, id);
        parkingWrapper.isNull(Parking::getEnd);
        Parking parking = parkingMapper.selectOne(parkingWrapper);

        parking.setEnd(new Date());
        parking.setTotalPrice(getDeltaDate(parking.getEnd(), parking.getStart()) * LIST_PRICE);
        parkingMapper.updateById(parking);

        //最后要将车位置为可用，生成订单
        parkingSpace.setCarNum("");
        parkingSpaceMapper.updateById(parkingSpace);

        Payment payment = new Payment();
        Car car = carMapper.selectById(id);
        payment.setFromUser(car.getOwner());
        payment.setType("parking");
        payment.setSum(parking.getTotalPrice());
        payment.setTime(new Date());
        paymentMapper.insert(payment);

        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "成功生成停车订单");
    }

    @Override
    public ResponseResult getLog() {
        List<Parking> parkingList = parkingMapper.selectList(null);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "成功获取所有停车记录", parkingList);
    }

    @Override
    public ResponseResult getLogByCarNum(String carNum) {
        //先查询有没有该车的停车记录
        LambdaQueryWrapper<Parking> parkingWrapper = new QueryWrapper<Parking>().lambda();
        parkingWrapper.eq(Parking::getCarNum, carNum);
        List<Parking> parkingList = parkingMapper.selectList(parkingWrapper);
        if(parkingList.size() == 0)
            return new ResponseResult(ResponseCode.NO_PARKING_LOG.getCode(), "未查询到该车辆在小区内有停车记录");
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "查询成功！", parkingList);
    }

    @Override
    public ResponseResult getCarInfo() {
        List<Car> cars = carMapper.selectList(null);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), cars);
    }

    @Override
    public ResponseResult getParkingSpaceInfo() {
        List<ParkingSpace> parkingSpaces = parkingSpaceMapper.selectList(null);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), parkingSpaces);
    }

    @Override
    public ResponseResult getInfoByUserid(String userid) {
        //先查询有没有该车的停车记录
        LambdaQueryWrapper<Car> carWrapper = new QueryWrapper<Car>().lambda();
        carWrapper.eq(Car::getOwner, userid);
        List<Car> cars = carMapper.selectList(carWrapper);
        if(cars.size() == 0)
            return new ResponseResult(ResponseCode.NO_CAR_LOG.getCode(), "未查询到该用户名下有车辆");
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "查询成功！", cars);
    }

    //添加车辆
    @Override
    public ResponseResult addCar(Car car) {
        Car res = carMapper.selectById(car.getId());
        if(res == null){
            int insert = carMapper.insert(car);
            if(insert > 0){
                return new ResponseResult(ResponseCode.SUCCESS.getCode(), "添加车辆成功！", car);
            }else{
                return new ResponseResult(ResponseCode.ERROR.getCode(), "服务器错误！");
            }
        }
        return new ResponseResult(ResponseCode.CAR_EXIST.getCode(), "该车辆已经存在！");
    }

    //添加停车位
    @Override
    public ResponseResult addParkingSpace(ParkingSpace parkingSpace) {
        ParkingSpace res = parkingSpaceMapper.selectById(parkingSpace.getId());
        //新添加的车位，停的车为空
        parkingSpace.setCarNum("");
        if(res == null){
            int insert = parkingSpaceMapper.insert(parkingSpace);
            if(insert > 0){
                return new ResponseResult(ResponseCode.SUCCESS.getCode(), "添加车位成功！", parkingSpace);
            }else{
                return new ResponseResult(ResponseCode.ERROR.getCode(), "服务器错误！");
            }
        }
        return new ResponseResult(ResponseCode.CAR_EXIST.getCode(), "该车位已经存在！");
    }

    //计算两个日期之间的时间差(小时数)
    public static double getDeltaDate(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        long day = diff / nd;
        long hour = diff % nd / nh;
        long min = diff % nd % nh / nm;

        double delta = 24 * day + hour + min/60.0;
        return delta;
    }
}
