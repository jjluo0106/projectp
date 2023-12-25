package com.heima.projectp.mapper;

import com.heima.projectp.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {

    void OperateLogInsert(OperateLog operateLog);
}
