package com.Mutation.Tr.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MainInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            String user = request.getUserPrincipal().getName();

        }catch(Exception e){
            e.printStackTrace();
            if(!response.isCommitted()){
                response.sendRedirect("/portfolio/main");
            }
//            return true;
        }

        return true;
    }
}
