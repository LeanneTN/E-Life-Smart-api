package org.csu.controller;

import io.jsonwebtoken.Claims;
import org.csu.uitls.JwtUtil;
import org.csu.vo.ResponseResult;
import org.csu.domain.Volunteer;
import org.csu.domain.VolunteerLog;
import org.csu.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/volunteer/")
public class VolunteerController {
    @Autowired
    private VolunteerService volunteerService;

    @PostMapping("/apply")
    public ResponseResult applyForVolunteer(@RequestBody Volunteer volunteer, HttpServletRequest request){
        String token = request.getHeader("token");
        Long uid = null;
        try{
            Claims claims = JwtUtil.parseJWT(token);
            uid = Long.valueOf(claims.getSubject());
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(uid);
        return volunteerService.applyForVolunteer(volunteer,uid);
    }

    //查看个人的志愿记录
    @GetMapping("/my_logs")
    public ResponseResult getMyLogs(HttpServletRequest request)
    {
        String token = request.getHeader("token");
        Long id = null;
        try{
            Claims claims = JwtUtil.parseJWT(token);
            id = Long.valueOf(claims.getSubject());
        }catch (Exception e){
            e.printStackTrace();
        }
        return volunteerService.getMyLogs(id);
    }

    //进行志愿活动
    @PostMapping("/my_logs")
    public ResponseResult insertMyLogs(@RequestBody VolunteerLog volunteerLog){
        return volunteerService.insertVolunteerLog(volunteerLog);
    }

    //查看所有人的志愿记录，为后台管理员使用
    @GetMapping("/logs")
    @PreAuthorize("hasAuthority('system:volunteer:log')")
    public ResponseResult getLogs(){
        return volunteerService.getLogs();
    }

    //按照所有人的id进行志愿记录分组返回，方便后台管理员查看每个人的情况
    @GetMapping("/logs_by_id")
    @PreAuthorize("hasAuthority('system:volunteer:log')")
    public ResponseResult getLogsGroupById(){
        return volunteerService.getLogsGroupById();
    }

    @GetMapping("/get_volunteer")
    public ResponseResult getVolunteer(HttpServletRequest request){
        String token = request.getHeader("token");
        Long uid = null;
        try{
            Claims claims = JwtUtil.parseJWT(token);
            uid = Long.valueOf(claims.getSubject());
        }catch (Exception e){
            e.printStackTrace();
        }
        return volunteerService.getVolunteer(uid);
    }

    @PostMapping("/get_volunteer_tasks")
    public ResponseResult getVolunteerTasks(HttpServletRequest request, @RequestBody Volunteer volunteer){
        String token = request.getHeader("token");
        Long uid = null;
        try{
            Claims claims = JwtUtil.parseJWT(token);
            uid = Long.valueOf(claims.getSubject());
        }catch (Exception e){
            e.printStackTrace();
        }
        String freeTime = volunteer.getFreeTime();
        return volunteerService.getVolunteerTasks(uid, freeTime);
    }


    @GetMapping("/all_volunteers")
    public ResponseResult getAllVolunteers() {
        return volunteerService.getAllVolunteers();
    }

    @DeleteMapping("/delete")
    public ResponseResult deleteVolunteer(HttpServletRequest request){
        String token = request.getHeader("token");
        Long uid = null;
        try{
            Claims claims = JwtUtil.parseJWT(token);
            uid = Long.valueOf(claims.getSubject());
        }catch (Exception e){
            e.printStackTrace();
        }

        return volunteerService.deleteVolunteer(uid);
    }

    @PostMapping("/update")
    public ResponseResult updateVolunteer(HttpServletRequest request, @RequestBody Volunteer volunteer){
        String token = request.getHeader("token");
        Long uid = null;
        try{
            Claims claims = JwtUtil.parseJWT(token);
            uid = Long.valueOf(claims.getSubject());
        }catch (Exception e){
            e.printStackTrace();
        }

        return volunteerService.updateVolunteer(uid, volunteer);
    }

    @PostMapping("/take_volunteer")
    public ResponseResult volunteerTake(HttpServletRequest request, @RequestBody VolunteerLog volunteerLog){
        String token = request.getHeader("token");
        Long uid = null;
        try{
            Claims claims = JwtUtil.parseJWT(token);
            uid = Long.valueOf(claims.getSubject());
        }catch (Exception e){
            e.printStackTrace();
        }

        return volunteerService.takeVolunteer(uid, volunteerLog);


    }
}
