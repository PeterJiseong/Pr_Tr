package com.Mutation.Tr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Column
    private String uri;

    @Column
    private String RemoteAddr;

    @Column
    private String time;

    public static AppropriateLog logToAppropriateLog(Log log) {
        return modelMapper.map(log, AppropriateLog.class);
    }
    public static InappropriateLog logToInappropriateLog(Log log) {
        return modelMapper.map(log, InappropriateLog.class);
    }
}
