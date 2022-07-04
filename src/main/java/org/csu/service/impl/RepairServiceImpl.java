package org.csu.service.impl;

import org.csu.domain.Repair;
import org.csu.vo.ResponseResult;
import org.csu.service.RepairService;
import org.springframework.stereotype.Service;

@Service
public class RepairServiceImpl implements RepairService {
    @Override
    public ResponseResult submitRepair(Repair repair) {
        return null;
    }

    @Override
    public ResponseResult getLogs() {
        return null;
    }

    @Override
    public ResponseResult getLogById(Long id) {
        return null;
    }

    @Override
    public ResponseResult getTasks() {
        return null;
    }

    @Override
    public ResponseResult updateStatus(Repair repair) {
        return null;
    }
}
