package org.csu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.domain.Raw;
import org.csu.domain.Repair;
import org.csu.domain.User;
import org.csu.mapper.RepairMapper;
import org.csu.service.UserService;
import org.csu.vo.ResponseCode;
import org.csu.vo.ResponseResult;
import org.csu.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {
    @Autowired
    private RepairMapper repairMapper;
    @Autowired
    private UserService userService;

    //提交报修
    @Override
    public ResponseResult submitRepair(Repair repair) {
        int insert = repairMapper.insert(repair);
        if(insert > 0){
            return new ResponseResult(ResponseCode.SUCCESS.getCode(), "成功提交报修");
        }else {
            return new ResponseResult(ResponseCode.ERROR.getCode(), "服务器错误");
        }
    }

    //获取所有的报修任务（状态为已报修）
    @Override
    public ResponseResult getTasks() {
        //首先查询该号码在不在库里
        LambdaQueryWrapper<Repair> wrapper = new QueryWrapper<Repair>().lambda();
        wrapper.eq(Repair::getStatus, "已报修");
        List<Repair> repairs = repairMapper.selectList(wrapper);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "成功获取所有报修任务");
    }

    //获取所有的报修信息，不管是什么状态的，为超级管理员所使用
    @Override
    public ResponseResult getLogs() {
        List<Repair> repairs = repairMapper.selectList(null);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "获取所有报修记录");
    }

    @Override
    public ResponseResult getLogById(Long id) {
        return null;
    }


    //更新维修单（维修者接单和用户确定）
    @Override
    public ResponseResult updateStatus(Repair repair) {
        int i = repairMapper.updateById(repair);
        if(i > 0)
            return new ResponseResult(ResponseCode.SUCCESS.getCode(), "成功更新维修单状态",repair);
        return new ResponseResult(ResponseCode.ERROR.getCode(), "服务器错误");
    }

    //获取当前用户的所有报修记录
    @Override
    public ResponseResult getLogByUserId(HttpServletRequest req) {
        User user = (User) userService.getLoginUser(req).getData();
        //首先查询该号码在不在库里
        LambdaQueryWrapper<Repair> wrapper = new QueryWrapper<Repair>().lambda();
        wrapper.eq(Repair::getFromUser, user.getId());
        List<Repair> repairs = repairMapper.selectList(wrapper);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "成功获取所有报修记录", repairs);
    }

    //更新某个Repair的内容
    @Override
    public ResponseResult updateRepairById(Repair repair) {
        int i = repairMapper.updateById(repair);
        if(i > 0)
            return new ResponseResult(ResponseCode.SUCCESS.getCode(), "成功修改");
        else
            return new ResponseResult(ResponseCode.ERROR.getCode(), "服务器错误");
    }

    //获取当前维修者的所有维修信息
    @Override
    public ResponseResult getMyRepair(HttpServletRequest req) {
        User user = (User) userService.getLoginUser(req).getData();
        LambdaQueryWrapper<Repair> wrapper = new QueryWrapper<Repair>().lambda();
        wrapper.eq(Repair::getRepairerId, user.getId());
        List<Repair> repairs = repairMapper.selectList(wrapper);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "成功获取所有维修信息", repairs);
    }
}