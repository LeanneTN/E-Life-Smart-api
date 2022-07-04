package org.csu.controller;

import org.csu.vo.ResponseResult;
import org.csu.domain.Volunteer;
import org.csu.domain.VolunteerLog;
import org.csu.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/volunteer/")
public class VolunteerController {
    @Autowired
    private VolunteerService volunteerService;

    @PostMapping("/apply")
    public ResponseResult applyForVolunteer(@RequestBody Volunteer volunteer){
        return volunteerService.applyForVolunteer();
    }

    //查看个人的志愿记录
    @GetMapping("/my_logs")
    public ResponseResult getMyLogs(){
        return volunteerService.getMyLogs();
    }

    //进行志愿活动
    @PostMapping("/my_logs")
    public ResponseResult insertMyLogs(@RequestBody VolunteerLog volunteerLog){
        return volunteerService.insertVolunteerLog(volunteerLog);
    }

    //查看所有人的志愿记录，为后台管理员使用
    @GetMapping("/logs")
    public ResponseResult getLogs(){
        return volunteerService.getLogs();
    }



}