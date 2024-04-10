package com.heima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperateLog {
    private Integer id; //id
    private Integer operateUser; //操作人
    private LocalDateTime operateTime; //操作時間
    private String className; //類名
    private String methodName; //方法名
    private String methodParams; //方法參數
    private String returnValue; //返回值
    private long costTime; //耗時
}
