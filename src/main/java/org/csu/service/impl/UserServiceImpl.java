package org.csu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.Producer;
import org.csu.mapper.UserMapper;
import org.csu.vo.CodeVO;
import org.csu.vo.LoginUser;
import org.csu.vo.ResponseCode;
import org.csu.vo.ResponseResult;
import org.csu.domain.User;
import org.csu.service.UserService;
import org.csu.uitls.JwtUtil;
import org.csu.uitls.RedisCache;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.zhenzi.sms.ZhenziSmsClient;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Producer captchaProducer;

    //手机验证码
    private String apiUrl = "https://sms_developer.zhenzikj.com";
    //榛子云系统上获取
    private String appId = "111119";
    private String appSecret = "638feb42-8ea3-4218-81c4-f7a370f0fa36";
    private String templateId = "8508";

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
    public ResponseResult getCaptcha(HttpServletRequest req, HttpServletResponse res) throws Exception{
        String codeStr = null;
        String code = null;
        BufferedImage image = null;
        // 生成验证码
        code = captchaProducer.createText();
        image = captchaProducer.createImage(code);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        ImageIO.write(image, "jpg", os);

        codeStr = Base64.encode(os.toByteArray());
        return new ResponseResult(200, "生成验证码成功", new CodeVO(codeStr, code));
    }


    @Override
    public ResponseResult loginByPhone(User user) {


        return null;
    }

    //注销
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

    //获取手机验证码
    @Override
    public ResponseResult getPhoneCode(String phoneNumber) {
        String phoneCode = null;
        try {
            //用于接收发送结果反馈 形式是JSON
            JSONObject result_json = new JSONObject();
            //随机生成验证码
            phoneCode = String.valueOf(new Random().nextInt(999999));
            //将验证码通过榛子云接口发送至手机
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);

            //发送短信
            Map<String, Object> params = new HashMap<String, Object>();//参数需要通过Map传递
            params.put("number", phoneNumber);
            params.put("templateId", templateId);
            String[] templateParams = new String[2];
            templateParams[0] = phoneCode;
            templateParams[1] = "5分钟";
            params.put("templateParams", templateParams);
            String result = client.send(params);

            result_json = JSONObject.parseObject(result);
            //发送短信失败
            if (result_json.getIntValue("code") != 0) {
                return new ResponseResult(ResponseCode.CODE_ERROR.getCode(), "获取验证码失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseResult(200, "验证码已发送", phoneCode);
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
        String originPassword = user.getPassword();
        //对密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //用户表插入
        userMapper.insert(user);
        //设置回原来的密码
        user.setPassword(originPassword);
        //注册之后顺带登录
        return loginByAccount(user);
    }

    //首先判断用户名是否被占用
    @Override
    public ResponseResult isUserNameExist(String userName) {
        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda();
        wrapper.eq(User::getUserName, userName);
        User res = userMapper.selectOne(wrapper);

        //如果查询结果为空
        if(res == null){
            return new ResponseResult(ResponseCode.SUCCESS.getCode(), "用户名可用");
        }else{
            return new ResponseResult(ResponseCode.USERNAME_EXIST.getCode(), "用户名被占用");
        }
    }

    //获取已登录账号的信息
    @Override
    public ResponseResult getLoginUser(HttpServletRequest req) {
        //首先取出请求头中的token
        //获取token(token在请求头中)
        String token = req.getHeader("token");
        //解析token
        String userid = null;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //从redis中获取用户信息
        String redisKey = "login:" + userid;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(loginUser)){
            return new ResponseResult(ResponseCode.NOT_LOGIN.getCode(), "未登录");
        }
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "成功获取用户信息", loginUser.getUser());
    }

    //检查手机号是否可用
    @Override
    public ResponseResult isPhoneNumberExist(String number) {
        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda();
        wrapper.eq(User::getPhoneNumber, number);
        User res = userMapper.selectOne(wrapper);

        //如果查询结果为空
        if(res == null){
            return new ResponseResult(ResponseCode.SUCCESS.getCode(), "手机号可用");
        }else{
            return new ResponseResult(ResponseCode.PHONE_EXIST.getCode(), "手机号被占用");
        }
    }

    //绑定手机号
    @Override
    public ResponseResult bindPhoneNumber(String number, HttpServletRequest req) {
        User user = (User)getLoginUser(req).getData();
        user.setPhoneNumber(number);
        int i = userMapper.updateById(user);
        if(i > 0){
            return new ResponseResult(ResponseCode.SUCCESS.getCode(), "绑定手机号成功");
        }
        return new ResponseResult(ResponseCode.ERROR.getCode(), "绑定手机号失败");
    }
}