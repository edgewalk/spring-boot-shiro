package com.edgewalk.springbootshiro.security.exception;

import com.edgewalk.springbootshiro.security.ApiResponse;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

/**
 * 安全异常处理器
 * Created by: edgewalk
 * 2019-03-27 22:31
 */
@RestControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(value = AuthorizationException.class)
    public ApiResponse handleAuthorizationException(AuthorizationException e){
        return ApiResponse.error(ApiResponse.Status.NOT_AUTHORIZATION);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ApiResponse handleAuthorizationException(AuthenticationException e){
        //TODO 添加切面信息,发生异常的类,方法,参数等信息
        return ApiResponse.error(ApiResponse.Status.NOT_LOGIN);
    }

//    @ExceptionHandler(value = Exception.class)
//    public ApiResponse handleException(Exception e){
//        //TODO 添加切面信息,发生异常的类,方法,参数等信息
//        return ApiResponse.error(ApiResponse.Status.INTERNAL_SERVER_ERROR);
//    }
}
