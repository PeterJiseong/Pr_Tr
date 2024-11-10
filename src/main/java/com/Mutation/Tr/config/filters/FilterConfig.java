package com.Mutation.Tr.config.filters;

import com.Mutation.Tr.observer.service.LoggingService;
import com.Mutation.Tr.util.service.GeoIpService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final LoggingService loggingService;
    private final GeoIpService geoIpService;

    @Bean
    public FilterRegistrationBean<LoggingFilter> customFilter() {
        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();

        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/favicon");
        urlPatterns.add("/portfolio");

        List<String> ignores = new ArrayList<>();
        ignores.add("/css");
        ignores.add("/js");
        ignores.add("/img");

        registrationBean.setFilter(new LoggingFilter(loggingService, urlPatterns, ignores, geoIpService));
        registrationBean.setOrder(1);  // 필터 순서 지정
        registrationBean.addUrlPatterns("/*");  // URL 패턴 지정

        return registrationBean;
    }
}
