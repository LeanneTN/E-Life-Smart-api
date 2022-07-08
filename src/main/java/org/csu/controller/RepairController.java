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
    public ResponseResult updateRepairById(@RequestBody Repair repair){
        return repairService.updateRepairById(repair);
    }

    //获取所有任务，用于维修者接单
    @GetMapping("/tasks")
    @PreAuthorize("hasAuthority('system:repair:tasks')")
    public ResponseResult getTasks(){
        return repairService.getTasks();
    }

    //获取某维修者的所有维修信息（当前接单+已完成）
    @GetMapping("/my_repair")
    @PreAuthorize("hasAuthority('system:repair:tasks')")
    public ResponseResult getMyRepair(HttpServletRequest req){
        return repairService.getMyRepair(req);
    }

    //以下两个函数都是不必要的，前端调用上面的接口再将数据处理可以达到相同的效果
    //获取某维修者某种类型的维修数（有门锁、家电、电路、水管四种类型）
    //获取某位维修者当前接的单

    //获取所有的报修，不管是（已报修/已接单/已完成）,该功能理论上为超级管理员使用
    @GetMapping("/logs")
    @PreAuthorize("hasAuthority('system:repair:tasks')")
    public ResponseResult getLogs(){
        return repairService.getLogs();
    }

    @GetMapping("/logs/{id}")
    public ResponseResult getLogById(@PathVariable("id") Long id){
        return repairService.getLogById(id);
    }

    //更新维修单（维修师傅的接单需要更新状态和维修者，用户的确认需要更新状态和结束时间）
    @PutMapping("/status")
    public ResponseResult updateStatus(@RequestBody Repair repair){
        return repairService.updateStatus(repair);
    }
}
