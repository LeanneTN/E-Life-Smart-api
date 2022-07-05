package org.csu.handler;

import com.alibaba.fastjson.JSON;
import org.csu.uitls.WebUtils;
import org.csu.vo.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//鉴权失败
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "您的权限不足");//401
        String json = JSON.toJSONString(result);
        //处理异常
        WebUtils.renderString(res, json);
    }
}
