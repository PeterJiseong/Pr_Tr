package com.Mutation.Tr.config.filters;

import jakarta.servlet.*;

import java.io.IOException;

public class LoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.err.println(servletRequest);
        System.out.println("servletResponse.getCharacterEncoding : " + servletResponse.getCharacterEncoding());
        System.out.println("servletResponse.getContentType : " + servletResponse.getContentType());
        System.out.println("servletResponse.getLocale : " + servletResponse.getLocale());
        System.out.println("servletResponse.getWriter : " + servletResponse.getWriter());
        System.out.println("servletResponse.getBufferSize : " + servletResponse.getBufferSize());
        System.out.println("servletResponse.getOutputStream : " + servletResponse.getOutputStream());
        System.out.println("servletResponse.isCommitted : " + servletResponse.isCommitted());
        System.out.println("------------------------------------------------------------------- ");

        System.err.println(servletResponse);
        System.out.println("servletResponse.get" + servletResponse.getOutputStream());
        System.out.println("servletResponse.get" + servletResponse.getWriter());
        System.out.println("servletResponse.get" + servletResponse.getLocale());
        System.out.println("servletResponse.get" + servletResponse.getCharacterEncoding());
        System.out.println("servletResponse.get" + servletResponse.getOutputStream());
        System.out.println("servletResponse.get" + servletResponse.getClass());
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
