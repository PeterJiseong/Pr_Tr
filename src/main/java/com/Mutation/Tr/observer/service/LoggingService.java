package com.Mutation.Tr.observer.service;

import com.Mutation.Tr.entity.Logging;
import com.Mutation.Tr.observer.repository.LoggingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoggingService {
    private final LoggingRepository loggingRepository;

    public void saveLogging(Map<String, String> contents){
        Logging logging = new Logging();
        logging.setRemoteAddr(contents.get("remoteAddr"));
        logging.setRequestUri(contents.get("requestURI"));
        logging.setTime(contents.get("time"));
        loggingRepository.save(logging);
    }

}
