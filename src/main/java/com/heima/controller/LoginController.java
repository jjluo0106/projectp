package com.heima.controller;
import com.heima.pojo.Result;
import com.heima.pojo.Emp;
import com.heima.service.EmpService;
import com.heima.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
@Slf4j
@RestController
public class LoginController {
    @Autowired
    EmpService empService;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        Emp login = empService.login(emp);
        String s = "";
        if(login != null){
            HashMap<String, Object> map = new HashMap<>();
            map.put("name",login.getName());
            map.put("password",login.getPassword());
            s = JwtUtils.generateJWT(map);
        }
        return login != null? Result.success(s):Result.error("登入失敗!!");
    }
}
