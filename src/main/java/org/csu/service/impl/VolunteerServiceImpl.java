package org.csu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", uid);
        Volunteer volunteer1 = volunteerMapper.selectOne(queryWrapper);
        System.out.println(volunteer1.getName());
        if(volunteer.getName()==null){
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

    @Override
    public ResponseResult getVolunteerTasks(long uid, String freeTime) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.isNull("volunteer_id");
        List<VolunteerLog> volunteerLogs = volunteerLogMapper.selectList(queryWrapper);
        System.out.println(volunteerLogs.size());
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "志愿任务获取成功", volunteerLogs);
    }

    @Override
    public ResponseResult getVolunteer(Long uid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", uid);
        Volunteer volunteer = volunteerMapper.selectOne(queryWrapper);
        if(volunteer == null)
            return new ResponseResult(ResponseCode.NOT_VOLUNTEER.getCode(), "无此志愿者");
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "志愿者获取成功", volunteer);
    }

    @Override
    public ResponseResult getAllVolunteers() {
        List<Volunteer> list = volunteerMapper.selectList(null);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(),"所用志愿者信息获取成功",list);
    }

    public ResponseResult deleteVolunteer(Long uid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", uid);
        volunteerMapper.delete(queryWrapper);
        volunteerLogMapper.delete(queryWrapper);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "删除成功");
    }

    @Override
    public ResponseResult updateVolunteer(Long uid, Volunteer volunteer) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", uid);
        volunteerMapper.update(volunteer, queryWrapper);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "数据库更新成功");
    }

    @Override
    public ResponseResult takeVolunteer(Long uid, VolunteerLog volunteerLog) {
        QueryWrapper queryWrapper = new QueryWrapper();
        volunteerLog.setVolunteerId(uid);
        volunteerLogMapper.updateById(volunteerLog);
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("uid", uid);
        double time = volunteerLog.getTotalTime();
        queryWrapper.eq("uid", uid);
        Volunteer volunteer = volunteerMapper.selectOne(queryWrapper);
        volunteer.setTotalTime(volunteer.getTotalTime()+time);
        volunteerMapper.update(volunteer, updateWrapper);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "任务接收成功", volunteerLog);
    }

    @Override
    public ResponseResult createVolunteer(Volunteer volunteer) {
        if(volunteer.getUid()+""==null){
            return new ResponseResult(ResponseCode.ERROR_DATA.getCode(), "缺少uid");
        }
        volunteerMapper.insert(volunteer);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "新增志愿者成功");
    }

    @Override
    public ResponseResult updateVolunteer(Volunteer volunteer) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("uid",volunteer.getUid());
        Volunteer temp = volunteerMapper.selectOne(wrapper);
        if (temp == null){
            return new ResponseResult(ResponseCode.EMPTY_VOLUNTEER.getCode(), "该志愿者不存在");
        }
        volunteerMapper.update(volunteer,wrapper);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "更新志愿者信息成功");
    }

    @Override
    public ResponseResult deleteVolunteerById(Long uid) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("uid",uid);
        Volunteer temp = volunteerMapper.selectOne(wrapper);
        if (temp == null){
            return new ResponseResult(ResponseCode.EMPTY_VOLUNTEER.getCode(), "该志愿者不存在");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid",uid);
        volunteerMapper.delete(queryWrapper);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "删除志愿者信息成功");
    }


}
