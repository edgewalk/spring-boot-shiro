package com.edgewalk.springbootshiro.security.exception;

import com.edgewalk.springbootshiro.security.ApiResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by: edgewalk
 * 2019-03-27 23:29
 */
@Controller
public class MyExceptionController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    private ErrorAttributes errorAttributes;

    @Autowired
    public MyExceptionController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     * Web页面错误处理,统一返回json
     */
   /* @RequestMapping(value = ERROR_PATH, produces = "text/html")
    @ResponseBody
    public ApiResponse errorPageHandler(HttpServletRequest request, HttpServletResponse response) {
        WebRequest requestAttributes = new ServletWebRequest(request);
        Map<String, Object> attr = this.errorAttributes.getErrorAttributes(requestAttributes, false);
        int status = getStatus(request);
        return ApiResponse.error(status, String.valueOf(attr.getOrDefault("message", "error")));
    }*/

    /**
     * 除Web页面外的错误处理，比如Json/XML等
     */
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public ApiResponse errorApiHandler(HttpServletRequest request) {
        WebRequest requestAttributes = new ServletWebRequest(request);
        Map<String, Object> attr = this.errorAttributes.getErrorAttributes(requestAttributes, false);
        int status = getStatus(request);
        return ApiResponse.error(status, String.valueOf(attr.getOrDefault("message", "error")));
    }

    private int getStatus(HttpServletRequest request) {
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (status != null) {
            return status;
        }
        return 500;
    }



    @RequestMapping("unauthorizedUrl")
    @ResponseBody
    public ApiResponse unauthorizedUrl(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        //TODO 如何获取当前用户信息
        return ApiResponse.success();
    }

}
