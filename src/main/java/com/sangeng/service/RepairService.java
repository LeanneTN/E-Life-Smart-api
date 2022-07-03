package com.sangeng.service;

import com.sangeng.domain.Repair;
import com.sangeng.vo.ResponseResult;

public interface RepairService {
    ResponseResult submitRepair(Repair repair);

    ResponseResult getLogs();

    ResponseResult getLogById(Long id);

    ResponseResult getTasks();

    ResponseResult updateStatus(Repair repair);
}
