package com.sa.youtube.infra.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoggerFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws IOException, ServletException {

        ContentCachingRequestWrapper cacheRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper cacheResponse = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(cacheRequest, cacheResponse);
    
        byte[] rawRequestBody = cacheRequest.getContentAsByteArray();
        byte[] rawResponseBody = cacheResponse.getContentAsByteArray();
        String requestBody = new String(rawRequestBody, StandardCharsets.UTF_8);
        String uriRequest = cacheRequest.getRequestURI();
        String ip = cacheRequest.getLocalAddr();
        String method = cacheRequest.getMethod();
        String responseBody = new String(rawResponseBody, StandardCharsets.UTF_8);
        Integer status = cacheResponse.getStatus();

        log.info("REQUEST: Method {}; URL: {}; IP: {}; BODY: {}", method, uriRequest, ip, requestBody);
        log.info("RESPONSE: Status: {}; BODY: {}", status, responseBody);
        cacheResponse.copyBodyToResponse();

    }

}