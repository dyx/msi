package com.lhd.msi.aspect;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * http请求日志记录
 *
 * @author lhd
 */
@Slf4j
@Aspect
@Component
public class HttpLogAop {

    private final ThreadLocal<TimeInterval> REQUEST_TIME = new ThreadLocal<>();

    @Value("${msi.log.http.enabled}")
    private Boolean enabled;

    @Pointcut("execution(public * com.lhd..controller.*Controller.*(..))")
    private void point() {
    }

    @Before("point()")
    public void before(JoinPoint joinPoint) {

        if (enabled) {
            try {

                REQUEST_TIME.set(DateUtil.timer());

                HttpServletRequest request = getRequest();
                log.info("********** HTTP Request **********");
                log.info("url：{} {}", request.getMethod(), request.getRequestURI());
                log.info("header：{}", getHeader(request));
                log.info("params：{}", JSONUtil.toJsonStr(joinPoint.getArgs()));
                log.info("classMethod：{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

            } catch (Exception e) {
                log.error("记录HTTP请求日志出错", e);
            }
        }
    }

    @AfterReturning(returning = "obj", pointcut = "point()")
    public void afterReturning(Object obj) {

        if (enabled) {
            try {

                HttpServletRequest request = getRequest();
                log.info("********** HTTP Response **********");
                log.info("url：{} {}", request.getMethod(), request.getRequestURI());
                log.info("header：{}", getHeader(request));
                log.info("body：{}", JSONUtil.toJsonStr(obj));
                log.info("耗时：{}ms", REQUEST_TIME.get().interval());

                // 防止线程复用造成的数据混乱
                REQUEST_TIME.remove();
            } catch (Exception e) {
                log.error("记录HTTP响应日志出错", e);
            }
        }
    }

    @AfterThrowing(throwing = "throwable", pointcut = "point()")
    public void afterThrowing(Throwable throwable) {
        if (enabled) {

            log.info("********** HTTP Error **********");
            HttpServletRequest request = getRequest();
            log.info("url：{} {}", request.getMethod(), request.getRequestURI());
            log.info("header：{}", getHeader(request));
            log.info("error: {}", throwable.getMessage());
            log.info("耗时：{}ms", REQUEST_TIME.get().interval());
            // 防止线程复用造成的数据混乱
            REQUEST_TIME.remove();
        }
    }

    private String getHeader(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            jsonObject.set(name, request.getHeader(name));
        }
        return jsonObject.toString();
    }

    private HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }
}
