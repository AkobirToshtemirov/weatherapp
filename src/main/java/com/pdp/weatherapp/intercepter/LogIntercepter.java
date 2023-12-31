package com.pdp.weatherapp.intercepter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Log
public class LogIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String contentType = request.getContentType();
        Map<String, String[]> parameterMap = request.getParameterMap();

        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        Iterator<String> iterator = headerNames.asIterator();
        while (iterator.hasNext()) {
            String headerName = iterator.next();
            String headerValue = request.getHeader(headerName);
            headers.put(headerName, headerValue);
        }

        StringBuilder sb = new StringBuilder();
        String logMessage = sb.append("{ path : ").append(requestURI).append(", ")
                .append("method : ").append(method).append(", ")
                .append("Content-Type : ").append(contentType).append(", ")
                .append("Parameters : ").append(parameterMap).append(", ")
                .append("Headers : ").append(headers).append(" }").toString();

        log.info(logMessage);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
