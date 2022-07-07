package org.csu.controller;

import org.csu.domain.Repair;
import org.csu.vo.ResponseResult;
import org.csu.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/repair/")
public class RepairController {
    @Autowired
    private RepairService repairService;

    //提交报修单
    @PostMapping("/submit")
    public ResponseResult submitRepair(@RequestBody Repair repair){
        return repairService.submitRepair(repair);
    }

    //获取当前用户的所有报修记录
    @GetMapping("/my_logs")
    public ResponseResult getLogByUserId(HttpServletRequest req){
        return repairService.getLogByUserId(req);
    }

    //根据ID修改某个报修的内容
    @PutMapping("/my_logs")
    public ResponseResult updateRepairById(Repair repair){
        return repairService.updateRepairById(repair);
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
