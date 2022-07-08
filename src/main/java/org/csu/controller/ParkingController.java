package org.csu.controller;

import io.jsonwebtoken.Claims;
import org.csu.domain.Car;
import org.csu.domain.ParkingSpace;
import org.csu.uitls.JwtUtil;
import org.csu.vo.ResponseResult;
import org.csu.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/parking/")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    //添加一辆车到小区的系统
    @PostMapping("/car")
    @PreAuthorize("hasAuthority('system:parking:add')")
    public ResponseResult addCar(@RequestBody Car car){
        return parkingService.addCar(car);
    }

    //添加一个车位到小区的系统
    @PostMapping("/parking_space")
    @PreAuthorize("hasAuthority('system:parking:add')")
    public ResponseResult addParkingSpace(@RequestBody ParkingSpace parkingSpace){
        return parkingService.addParkingSpace(parkingSpace);
    }

    @PostMapping("/park")
    public ResponseResult park(@RequestParam("id") String id){
        return parkingService.park(id);
    }

    @PostMapping("/leave")
    public ResponseResult leave(@RequestParam("id") String id){
        return parkingService.leave(id);
    }

    @PostMapping("/own")
    public ResponseResult ifOwns(HttpServletRequest request){
        String token = request.getHeader("token");
        Long uid = null;
        try{
            Claims claims = JwtUtil.parseJWT(token);
            uid = Long.valueOf(claims.getSubject());
        }catch (Exception e){
            e.printStackTrace();
        }
        return parkingService.ownCar(uid);
    }

    //获取所有的停车记录，以用于后台展示
    @GetMapping("/log")
    @PreAuthorize("hasAuthority('system:parking:log')")
    public ResponseResult getLog(){
        return parkingService.getLog();
    }

    //获取某辆车的停车记录
    @GetMapping("/log/{carNum}")
    @PreAuthorize("hasAuthority('system:parking:log')")
    public ResponseResult getLogByCarNum(@PathVariable("carNum") String carNum){
        return parkingService.getLogByCarNum(carNum);
    }

    //获取小区内所有车辆的信息
    @GetMapping("/info/car")
    @PreAuthorize("hasAuthority('system:parking:info')")
    public ResponseResult getCarInfo(){
        return parkingService.getCarInfo();
    }

    //获取所有车位的信息
    @GetMapping("/info/parking_space")
    @PreAuthorize("hasAuthority('system:parking:info')")
    public ResponseResult getParkingSpaceInfo(){
        return parkingService.getParkingSpaceInfo();
    }

    //查询某用户名下的车辆信息
    @GetMapping("/info/{userid}")
    @PreAuthorize("hasAuthority('system:parking:info')")
    public ResponseResult getInfoByUserid(@PathVariable("userid") String userid){
        return parkingService.getInfoByUserid(userid);
    }
}
