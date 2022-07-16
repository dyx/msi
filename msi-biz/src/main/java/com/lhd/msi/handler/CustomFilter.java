package com.lhd.msi.handler;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 自定义过滤器
 * @author lhd
 */
@Slf4j
@WebFilter(filterName = "customFilter", urlPatterns = "/*")
public class CustomFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("自定义过滤器，请求前");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("自定义过滤器，请求后");
    }
}
