package com.heima.projectp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Log {
    private LocalDateTime createTime;
    private String description;


    /**
     * 創建日誌
     * @param description
     * @return
     */
    public Log Log(String description){
        return new Log(LocalDateTime.now(), description);
    }
}
