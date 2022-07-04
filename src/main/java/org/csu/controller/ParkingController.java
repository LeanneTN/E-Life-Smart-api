package org.csu.controller;

import org.csu.vo.ResponseResult;
import org.csu.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking/")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @PostMapping("/park")
    public ResponseResult park(@RequestParam("imgPath") String imgPath){
        return parkingService.park();
    }

    @PostMapping("/leave")
    public ResponseResult leave(@RequestParam("imgPath") String imgPath){
        return parkingService.leave();
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
