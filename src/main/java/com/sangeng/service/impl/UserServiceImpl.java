package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sangeng.mapper.UserMapper;
import com.sangeng.vo.LoginUser;
import com.sangeng.vo.ResponseResult;
import com.sangeng.domain.User;
import com.sangeng.service.UserService;
import com.sangeng.uitls.JwtUtil;
import com.sangeng.uitls.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult loginByAccount(User user) {
        //获取authenticate进行认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //如果认证没通过，给出提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登陆失败");
        }
        //认证通过，使用userId生成jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);

        //完整的用户存入redis，jwt作为key
        redisCache.setCacheObject("login:" + userid, loginUser);

        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return new ResponseResult(200, "登陆成功", map);
    }

    @Override
    public ResponseResult loginByPhone(User user) {
        return null;
    }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        //删除redis中的值
        redisCache.deleteObject("login:" + userid);
        return new ResponseResult(200, "注销成功");
    }

    @Override
    public ResponseResult getPhoneCode() {
        return null;
    }

    @Override
    public ResponseResult getCaptcha() {
        return null;
    }

    @Override
    public ResponseResult getUserInfoById(String userid) {
        return null;
    }

    @Override
    public ResponseResult updateUserInfoById(User user) {
        return null;
    }

    //注册
    @Override
    public ResponseResult register(User user) {
        //首先判断用户名是否被占用
        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda();
        wrapper.eq(User::getUserName, user.getUserName());
        User res = userMapper.selectOne(wrapper);

        //如果查询结果为空
        if(res == null){
//            return new ResponseResult(200, )
        }

        return null;
    }
}
