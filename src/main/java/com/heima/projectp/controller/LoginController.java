package com.heima.projectp.controller;

import com.heima.projectp.mapper.EmpMapper;
import com.heima.projectp.pojo.Emp;
import com.heima.projectp.pojo.Result;
import com.heima.projectp.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    EmpService empService;

    @PostMapping
    public Result login(Emp emp){
        Integer isExist = empService.login(emp);
        return isExist>0? Result.msg("登入成功"):Result.error();
    }
}
