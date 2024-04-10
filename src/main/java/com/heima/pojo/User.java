package com.heima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    Integer id;
    String account;
    String passWord;
    String userName;
    String image;
    Integer level;
    String createTime;
    String updateTime;

}
