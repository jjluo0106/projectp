package com.heima.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface LogMapper {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void LogInsert(String des);

}
