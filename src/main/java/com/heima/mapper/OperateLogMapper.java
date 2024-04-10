package com.heima.mapper;

import com.heima.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {

    void OperateLogInsert(OperateLog operateLog);
}
