package com.sangeng.controller;

import com.sangeng.domain.Repair;
import com.sangeng.vo.ResponseResult;
import com.sangeng.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repair/")
public class RepairController {
    @Autowired
    private RepairService repairService;

    @PostMapping("/submit")
    public ResponseResult submitRepair(@RequestBody Repair repair){
        return repairService.submitRepair(repair);
    }

    @GetMapping("/logs")
    public ResponseResult getLogs(){
        return repairService.getLogs();
    }

    @GetMapping("/logs/{id}")
    public ResponseResult getLogById(@PathVariable("id") Long id){
        return repairService.getLogById(id);
    }

    //获取所有任务
    @GetMapping("/tasks")
    @PreAuthorize("hasAuthority('system:repair:tasks')")
    public ResponseResult getTasks(){
        return repairService.getTasks();
    }

    //更新状态
    @PutMapping("/status")
    public ResponseResult updateStatus(@RequestBody Repair repair){
        return repairService.updateStatus(repair);
    }


}
