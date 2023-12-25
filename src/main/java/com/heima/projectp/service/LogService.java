package com.heima.projectp.service;

import com.heima.projectp.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogService {

    @Autowired
    LogMapper logMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert(String des){
        logMapper.LogInsert(des);
    }
}
