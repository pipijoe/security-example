package com.les.config.security;

import com.les.domain.ResultCode;
import com.les.domain.ResultJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登陆状态下，权限不足执行该方法
 * @author Joetao
 * @date 2022/6/9
 */
@Component()
@Slf4j
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        log.error("权限不足：{}，{}", e.getMessage(), httpServletRequest.getRequestURI());
        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        String body = ResultJson.failure(ResultCode.FORBIDDEN, "没有权限").toString();
        printWriter.write(body);
        printWriter.flush();
    }
}
