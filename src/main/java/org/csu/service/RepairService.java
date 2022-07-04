package org.csu.service;

import org.csu.domain.Repair;
import org.csu.vo.ResponseResult;

public interface RepairService {
    ResponseResult submitRepair(Repair repair);

    ResponseResult getLogs();

    ResponseResult getLogById(Long id);

    ResponseResult getTasks();

    ResponseResult updateStatus(Repair repair);
}
