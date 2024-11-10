package com.Mutation.Tr.util.service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Slf4j
@Service
public class GeoIpService {
    private DatabaseReader dbReader;
    @Value("${maxmind.api.database.path}")
    private String geoIpPath;

    @PostConstruct
    public void init() throws IOException {
        // DB 파일 로드
        File database = new File(geoIpPath);
        dbReader = new DatabaseReader.Builder(database).build();
    }

    public CityResponse getLocation(String ipAddress) {
        try {
            InetAddress ip = InetAddress.getByName(ipAddress);

            return dbReader.city(ip);


        } catch (Exception e) {
            log.error("IP 위치 조회 실패: " + ipAddress, e);
            return null;
        }
    }
}
