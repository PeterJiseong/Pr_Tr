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
import java.time.ZoneId;
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
        System.err.println(requestURI);
        CityResponse cityResponse = geoIpService.getLocation(remoteAddr);
        Log log = new Log();
        if(cityResponse != null) {
            System.err.println("city : " + cityResponse.getCity());
            log = log
                    .withRemoteAddr(remoteAddr)
                    .withTime(LocalDateTime.now(ZoneId.of("Asia/Seoul")).toString())
                    .withUri(requestURI)
                    .withCity(cityResponse.getCity().getName())
                    .withCountry(cityResponse.getCountry().getName())
                    .withLocation(cityResponse.getLocation().getTimeZone())
                    .withPostalCode(cityResponse.getPostal().getCode());

        }else{
            log = log
                    .withRemoteAddr(remoteAddr)
                    .withTime(LocalDateTime.now().toString())
                    .withUri(requestURI)
                    .withCity("null")
                    .withCountry("null")
                    .withLocation("null")
                    .withPostalCode("null");

        }

        if(isInappropriateUri(requestURI)){
            filterChain.doFilter(request, response);
            System.err.println("which");

        }else if(isStaticResource(requestURI)){
            filterChain.doFilter(request, response);
            System.err.println("one");
            return;
        } else{
            loggingService.saveInappropriateLog(log);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.err.println(request.getRequestURI());
            System.err.println("is");
            return;
        }


        loggingService.saveAppropriageLog(log);
        System.err.println("requestURI : " + requestURI);
        System.err.println("requestURL : " + request.getRequestURL());
        System.err.println("request.Header ::");
        request.getHeaderNames().asIterator().forEachRemaining((o)->{
            System.err.println(o + " : " + request.getHeader(o).toString());
        });

        System.err.println("request.getLocalPort : " + request.getLocalPort());
        System.err.println("request.getMethod : " + request.getMethod());
        System.err.println("request.getParameterMap :  : " + request.getParameterMap());
        request.getParameterMap().forEach((k,v)->{
            System.err.println(k + " : " + Arrays.toString((String[]) v) );
        });
        System.err.println("request.getPathInfo : " + request.getPathInfo());
        System.err.println("request.getQueryString : " + request.getQueryString());
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

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        String uri = request.getRequestURI();
//        return ignoredURIs.stream()
//                .noneMatch(uri::startsWith);
//    }
}
