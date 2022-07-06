package org.csu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.domain.Volunteer;
import org.csu.vo.ResponseCode;
import org.csu.vo.ResponseResult;
import org.csu.domain.VolunteerLog;
import org.csu.mapper.VolunteerLogMapper;
import org.csu.mapper.VolunteerMapper;
import org.csu.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Autowired
    private VolunteerMapper volunteerMapper;
    @Autowired
    private VolunteerLogMapper volunteerLogMapper;

    @Override
    public ResponseResult applyForVolunteer(Volunteer volunteer, long uid) {
        Volunteer volunteer1 = volunteerMapper.selectById(uid);
        if(volunteer.getName().equals(null)){
            return new ResponseResult(ResponseCode.USER_VOLUNTEER_INFO_INCOMPLETE.getCode(), "志愿者信息不完整");
        }
        if(volunteer1==null){
            volunteer.setUid(uid);
            volunteerMapper.insert(volunteer);
            return new ResponseResult(ResponseCode.SUCCESS.getCode(), "志愿者创建成功");
        }

        return new ResponseResult(ResponseCode.USERNAME_EXIST.getCode(), "志愿者已存在");
    }

    @Override
    public ResponseResult getMyLogs(long volunteer_id) {
        QueryWrapper<VolunteerLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("volunteer_id", volunteer_id);
        ArrayList<VolunteerLog> list = (ArrayList<VolunteerLog>) volunteerLogMapper.selectList(queryWrapper);
        if(list==null)
            return new ResponseResult(ResponseCode.USER_VOLUNTEER_LOG_EMPTY.getCode(), "用户记录为空");
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "用户志愿记录读取成功", list);
    }

    @Override
    public ResponseResult insertVolunteerLog(VolunteerLog volunteerLog) {
        volunteerLogMapper.insert(volunteerLog);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "用户日志插入成功", volunteerLog);
    }

    @Override
    public ResponseResult getLogs() {
        ArrayList<VolunteerLog> list = (ArrayList<VolunteerLog>) volunteerLogMapper.selectList(null);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "所有日志返回成功", list);
    }

    @Override
    public ResponseResult getLogsGroupById() {
        QueryWrapper<VolunteerLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("volunteer_id", "event", "sum(total_time)");
        queryWrapper.groupBy("volunteer_id");
        List<VolunteerLog> volunteerList = volunteerLogMapper.selectList(queryWrapper);
        List<VolunteerLog> volunteerLogsTime = volunteerLogMapper.selectList(null);
        for(int i = 0; i < volunteerList.size(); i++){
            int total_time = 0;
            Long id = volunteerList.get(i).getVolunteerId();
            for(int j = 0; j < volunteerLogsTime.size(); j++){
                if(Objects.equals(id, volunteerLogsTime.get(j).getVolunteerId()))
                    total_time+=volunteerLogsTime.get(j).getTotalTime();
            }
            volunteerList.get(i).setTotalTime(total_time);
        }
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "按id分组进行获取日志成功",volunteerList);
    }
}
