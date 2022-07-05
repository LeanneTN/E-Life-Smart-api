package org.csu.controller;

import org.csu.domain.Car;
import org.csu.domain.ParkingSpace;
import org.csu.vo.ResponseResult;
import org.csu.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking/")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @PostMapping("/car")
    @PreAuthorize("hasAuthority('system:parking:add')")
    public ResponseResult addCar(@RequestBody Car car){
        return parkingService.addCar(car);
    }

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

    @GetMapping("/log")
    @PreAuthorize("hasAuthority('system:parking:log')")
    public ResponseResult getLog(){
        return parkingService.getLog();
    }

    @GetMapping("/log/{carNum}")
    @PreAuthorize("hasAuthority('system:parking:log')")
    public ResponseResult getLogByCarNum(@PathVariable("carNum") String carNum){
        return parkingService.getLogByCarNum(carNum);
    }

    @GetMapping("/info")
    @PreAuthorize("hasAuthority('system:parking:info')")
    public ResponseResult getInfo(){
        return parkingService.getInfo();
    }

    @GetMapping("/info/{userid}")
    @PreAuthorize("hasAuthority('system:parking:info')")
    public ResponseResult getInfoByUserid(@PathVariable("userid") String userid){
        return parkingService.getInfoByUserid(userid);
    }
}
