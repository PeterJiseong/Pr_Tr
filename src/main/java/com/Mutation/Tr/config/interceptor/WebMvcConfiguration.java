package com.Mutation.Tr.config.interceptor;

import com.sun.tools.javac.Main;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {
    private final MainInterceptor mainInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        List<String> exceptionPath = new ArrayList<>();
        exceptionPath.add("/css/**");
        exceptionPath.add("/js/**");
        exceptionPath.add("/img/**");


        registry.addInterceptor(mainInterceptor)
                .excludePathPatterns(exceptionPath)
                .addPathPatterns("")
                .addPathPatterns("/");


    }
}
