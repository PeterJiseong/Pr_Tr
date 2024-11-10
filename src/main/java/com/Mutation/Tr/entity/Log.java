package com.Mutation.Tr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

@MappedSuperclass
@Getter @Setter @ToString
public class Log {


    private static ModelMapper modelMapper = new ModelMapper();

    public Log withRemoteAddr(String remoteAddr) {
        this.setRemoteAddr(remoteAddr);
        return this;
    }
    public Log withTime(String time) {
        this.setTime(time);
        return this;
    }
    public Log withUri(String uri) {
        this.setUri(uri);
        return this;
    }
    public Log withCountry(String country) {
        this.setCountry(country);
        return this;
    }
    public Log withCity(String city) {
        this.setCity(city);
        return this;
    }
    public Log withLocation(String location) {
        this.setLocation(location);
        return this;
    }
    public Log withPostalCode(String postalCode) {
        this.postal = postalCode;
        return this;
    }

    @Column
    private String uri;

    @Column
    private String RemoteAddr;

    @Column
    private String time;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String location;

    @Column
    private String postal;

    public static AppropriateLog logToAppropriateLog(Log log) {
        return modelMapper.map(log, AppropriateLog.class);
    }
    public static InappropriateLog logToInappropriateLog(Log log) {
        return modelMapper.map(log, InappropriateLog.class);
    }
}
