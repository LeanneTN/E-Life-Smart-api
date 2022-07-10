package org.csu.service;

import org.csu.domain.Repair;
import org.csu.vo.ResponseResult;

import javax.servlet.http.HttpServletRequest;

public interface RepairService {
    ResponseResult submitRepair(Repair repair);

    ResponseResult getLogs();

    //删除维修记录
    ResponseResult deleteRepairLog(Long id);

    ResponseResult getLogById(Long id);

    ResponseResult getTasks();

    ResponseResult updateStatus(Repair repair);

    ResponseResult getLogByUserId(HttpServletRequest req);

    ResponseResult updateRepairById(Repair repair);

    ResponseResult getMyRepair(HttpServletRequest req);

    ResponseResult deleteRepairById(Long id);

}
