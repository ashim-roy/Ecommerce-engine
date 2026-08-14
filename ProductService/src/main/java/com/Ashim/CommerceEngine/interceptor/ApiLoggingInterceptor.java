package com.Ashim.CommerceEngine.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiLoggingInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(ApiLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        request.setAttribute("startTime", System.currentTimeMillis());

        log.info("Incoming Request => {} {}",
                request.getMethod(),
                request.getRequestURI());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) {

        long startTime = (Long) request.getAttribute("startTime");
        long timeTaken = System.currentTimeMillis() - startTime;

        log.info("Completed => {} {} | Status: {} | Time: {} ms",
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(),
                timeTaken);
    }
}