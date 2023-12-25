package com.heima.projectp.controller;

import com.heima.projectp.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

@Scope("prototype")
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    DeptService deptService;


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) throws Exception {
        deptService.deleteById(id);
    }
}
