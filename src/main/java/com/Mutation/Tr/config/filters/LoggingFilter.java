package com.Mutation.Tr.config.filters;

import com.Mutation.Tr.entity.Log;
import com.Mutation.Tr.observer.service.LoggingService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@WebFilter(urlPatterns = "/*")
@Component
@RequiredArgsConstructor
public class LoggingFilter extends OncePerRequestFilter {

    private final LoggingService loggingService;
    private static List<String> correctURIs;

    @Override
    protected void initFilterBean() throws ServletException {
        this.correctURIs = new ArrayList<>();
        correctURIs.add("/favicon.ico");
        correctURIs.add("/portfolio");
        super.initFilterBean();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        Log log = new Log()
                .withRemoteAddr(request.getRemoteAddr())
                .withTime(LocalDateTime.now().toString())
                .withUri(requestURI);

        if(isInappropriateUri(requestURI)){
            filterChain.doFilter(request, response);
        }else{
            loggingService.saveInappropriateLog(log);
            return;
        }

        doFilter(request, response, filterChain);
        if (requestURI.endsWith(".css") || requestURI.endsWith(".js") || requestURI.endsWith(".jpg")) {
            return; // 필터 실행 없이 바로 반환
        }
        loggingService.saveAppropriageLog(log);
        System.err.println("requestURI : " + requestURI);
        System.err.println("requestURL : " + request.getRequestURL());
//        System.err.println("request.getAuthType : " + request.getAuthType());
//        System.err.println("request.getContextPath : " + request.getContextPath());
//        System.err.println("request.getCookies : " + request.getCookies());
//        System.err.println("request.getHttpServletMapping : " + request.getHttpServletMapping());
        System.err.println("request.Header ::");
        request.getHeaderNames().asIterator().forEachRemaining((o)->{
            System.err.println(o + " : " + request.getHeader(o).toString());
        });
//
//
//        System.err.println("request.getLocale : " + request.getLocale());
//        System.err.println("request.getLocalName : " + request.getLocalName());
        System.err.println("request.getLocalPort : " + request.getLocalPort());
        System.err.println("request.getMethod : " + request.getMethod());
        System.err.println("request.getParameterMap :  : " + request.getParameterMap());
        request.getParameterMap().forEach((k,v)->{
            System.err.println(k + " : " + Arrays.toString((String[]) v) );
        });
        System.err.println("request.getPathInfo : " + request.getPathInfo());
//        System.err.println("request.getPathTranslated : " + request.getPathTranslated());
//        System.err.println("request.getProtocol : " + request.getProtocol());
//        System.err.println("request.getRemoteUser : " + request.getRemoteUser());
        System.err.println("request.getQueryString : " + request.getQueryString());
//        System.err.println("request.getRequestedSessionId : " + request.getRequestedSessionId());
//        System.err.println(request.getLocalAddr());
//        System.err.println(request.getRequestedSessionId() + " ::: iddididid");
//        System.err.println("request.getUserPrincipal : " + request.getUserPrincipal());
//        System.err.println("request.getRequestId : " + request.getRequestId());
//        System.err.println("request.getRequestURI : " + request.getRequestURI());
//        System.err.println("request.getRequestURL : " + request.getRequestURL());
//        System.err.println("request.getServerName : " + request.getServerName());
//        System.err.println("request.getServerPort : " + request.getServerPort());
        System.err.println("end \n\n\n");
    }

    private boolean isInappropriateUri(String uri) {
        for(String appropriateUri : correctURIs){
            if(appropriateUri.equals(uri)) return true;
        }
        return false;
    }

}
