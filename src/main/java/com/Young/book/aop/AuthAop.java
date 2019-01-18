package com.Young.book.aop;

import com.Young.book.po.Librarian;
import com.Young.book.service.LibrarianService;
import com.Young.book.utils.JWT;
import com.Young.book.utils.Result;
import com.Young.book.utils.ResultEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Aspect
@Component
public class AuthAop {
    @Autowired
    private LibrarianService librarianService;

    @Pointcut("execution(public * com.Young.book.controller.*.*(..))"
            + "&& !execution(public * com.Young.book.controller.LibrarianController.login(..))"
            + "&& !execution(public * com.Young.book.controller.LibrarianController.regist(..))"
    )
    public void pointCut(){

    }
    @Around("pointCut()")
    public Object handler(ProceedingJoinPoint pj) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String token = request.getHeader("token");
        if(token != null && token != ""){
            Map res = JWT.verifiyToken(token);
            if(res != null){
                Librarian librarian = this.librarianService.getLibrarianById(res.get("id").toString());
                if(librarian != null){
                    return pj.proceed();
                } else {
                    return new Result<>(ResultEnum.TOKEN_ERROR, "token错误");
                }
            } else {
                return new Result<>(ResultEnum.TOKEN_ERROR, "token错误");
            }
        } else {
            return new Result<>(ResultEnum.NO_TOKEN, "请添加token");
        }
    }
}
