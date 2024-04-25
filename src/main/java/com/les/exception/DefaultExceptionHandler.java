package com.les.exception;

import com.les.domain.ResultCode;
import com.les.domain.ResultJson;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理类
 *
 * @author jt
 */
//@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {
    /**
     * 处理自定义异常: 登录异常
     */
    @ExceptionHandler({LoginFailedException.class})
    public ResultJson<?> handleLoginBaseException(LoginFailedException e) {
        return ResultJson.failure(ResultCode.UNAUTHORIZED, e.getMessage());
    }
}
