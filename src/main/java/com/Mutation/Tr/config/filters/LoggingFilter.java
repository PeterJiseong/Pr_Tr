package com.Mutation.Tr.config.filters;

import com.Mutation.Tr.entity.Log;
import com.Mutation.Tr.observer.service.LoggingService;
import com.Mutation.Tr.util.service.GeoIpService;
import com.maxmind.geoip2.model.CityResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = "/*")

public class LoggingFilter extends OncePerRequestFilter {

    private final LoggingService loggingService;
    private final GeoIpService geoIpService;
    private final List<String> correctURIs;
    private final List<String> ignoredURIs;


    public LoggingFilter(LoggingService loggingService, List<String> correctURIs, List<String> ignoredURIs, GeoIpService geoIpService) {
        this.loggingService = loggingService;
        this.correctURIs = correctURIs;
        this.ignoredURIs = ignoredURIs;
        this.geoIpService = geoIpService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();
        CityResponse cityResponse = geoIpService.getLocation(remoteAddr);

        Log log = new Log()
                .withRemoteAddr(remoteAddr)
                .withTime(LocalDateTime.now().toString())
                .withUri(requestURI)
                .withCity(cityResponse.getCity().toString())
                .withCountry(cityResponse.getCountry().toString())
                .withLocation(cityResponse.getLocation().toString())
                .withPostalCode(cityResponse.getPostal().toString());

        if(isInappropriateUri(requestURI)){
            filterChain.doFilter(request, response);

        }else if(isStaticResource(requestURI)){
            filterChain.doFilter(request, response);
            return;
        } else{
            loggingService.saveInappropriateLog(log);
            System.err.println(request.getRequestURI());
            return;
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
        if(uri.endsWith("/")) return true;
        for(String appropriateUri : correctURIs){
            if(uri.startsWith(appropriateUri)) return true;
        }
        return false;
    }
    private boolean isStaticResource(String uri) {
        for(String ignore : ignoredURIs){
            if(uri.startsWith(ignore)){
                return true;
            }
        }

        return false;
    }

}
