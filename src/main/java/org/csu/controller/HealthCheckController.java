package org.csu.controller;

import org.csu.domain.HealthCheck;
import org.csu.vo.ResponseResult;
import org.csu.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health/")
public class HealthCheckController {
    @Autowired
    private HealthCheckService healthCheckService;

    @PostMapping("/submit")
    public ResponseResult submit(@RequestBody HealthCheck healthCheck){
        return healthCheckService.submit(healthCheck);
    }

    //查看打卡情况
    @PostMapping("/info")
    @PreAuthorize("hasAuthority('system:health:info')")
    public ResponseResult getInfo(){
        return healthCheckService.getInfo();
    }

    //按照id查看住户打卡情况
    @PostMapping("/info/{id}")
    @PreAuthorize("hasAuthority('system:health:info')")
    public ResponseResult getInfoById(@PathVariable("id") long healthId){
        return  healthCheckService.getInfoById(healthId);
    }
}
