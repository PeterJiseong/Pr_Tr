package com.Mutation.Tr.config.filters;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Component
public class LoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       filterChain.doFilter(servletRequest, servletResponse);

        System.err.println(servletRequest);
//        System.out.println("servletResponse.get" + servletResponse.getOutputStream());
        System.err.println("serveletRequest.getCharacterEncoding : "+servletRequest.getCharacterEncoding());
        System.err.println("serveletRequest.getContentType : "+servletRequest.getContentType());
        System.err.println("serveletRequest.getRequestId : "+servletRequest.getRequestId());
        System.err.println("serveletRequest.getLocale : "+servletRequest.getLocale());
        System.err.println("serveletRequest.getProtocolRequestId : "+servletRequest.getProtocolRequestId());
        System.err.println("serveletRequest.getLocalAddr : "+servletRequest.getLocalAddr());
        System.err.println("serveletRequest.getLocalName : "+servletRequest.getLocalName());
        System.err.println("serveletRequest.getParameterMap : "+servletRequest.getParameterMap().toString());
        Map map = servletRequest.getParameterMap();
        System.err.println("map : -->");
        map.forEach((o, o2) -> System.err.println(o + " : " +  Arrays.toString((String[]) o2)));
        System.err.println("serveletRequest.getProtocol : "+servletRequest.getProtocol());
        System.err.println("serveletRequest.getRemoteAddr : "+servletRequest.getRemoteAddr());
        System.err.println("serveletRequest.getContentLength : "+servletRequest.getContentLength());
        System.err.println("serveletRequest.getRemoteHost : "+servletRequest.getRemoteHost());
        System.err.println("serveletRequest.getRemotePort : "+servletRequest.getRemotePort());
//        System.err.println("serveletRequest.get : "+servletRequest.getRequestDispatcher().);
        System.err.println("serveletRequest.getScheme : "+servletRequest.getScheme());
        System.err.println("serveletRequest.getServerName : "+servletRequest.getServerName());
        System.err.println("serveletRequest.getServerPort : "+servletRequest.getServerPort());
        System.err.println("serveletRequest.getServletConnection : "+servletRequest.getServletConnection());
        System.err.println("serveletRequest.getServletContext : "+servletRequest.getServletContext());
        System.err.println("end \n\n\n");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
