package com.Mutation.Tr.observer.service;

import com.Mutation.Tr.entity.InappropriateLog;
import com.Mutation.Tr.entity.AppropriateLog;
import com.Mutation.Tr.entity.Log;
import com.Mutation.Tr.observer.repository.AppropriateLogRepository;
import com.Mutation.Tr.observer.repository.InappropriateLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoggingService {
    private final AppropriateLogRepository appropriateLogRepository;
    private final InappropriateLogRepository inappropriateLogRepository;

    public AppropriateLog saveAppropriageLog(Log log){
        return appropriateLogRepository.save(Log.logToAppropriateLog(log));
    }

    public InappropriateLog saveInappropriateLog(Log log){
        return inappropriateLogRepository.save(Log.logToInappropriateLog(log));
    }
}