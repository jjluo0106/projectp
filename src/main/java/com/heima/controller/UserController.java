package com.heima.controller;
import com.heima.pojo.Result;
import com.heima.pojo.User;
import com.heima.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")  //抽取路徑前墜
@Api(tags = "用戶相關接口")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 新增
     * @param user
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("創建用戶")
    public Result userInsert(@RequestBody User user){
        log.info("創建用戶");
        userService.UserInsert(user);
        return Result.success("新增成功");
    }

    @DeleteMapping("/delete/{ids}")
    public Result userDeleteById(@PathVariable List<Integer> ids){
        log.info("刪除用戶");
        int i = userService.userDelete(ids);
        if(i>0){
            log.info("刪除成功，刪除了: {}", i);
        }else {
            log.info("刪除失敗");
        }
        return Result.success("刪除成功");
    }


    @PutMapping("/update")
    public Result userUpdate(@RequestBody User user){
        log.info("修改用戶");
        userService.userUpdate(user);

        return Result.success("修改成功");
    }

    @GetMapping("/selectAll")
    public Result userSelectAll(){
        log.info("查詢用戶");
        List<User> user = userService.userSelectAll();
        return Result.success(user);
    }

    @GetMapping("/selectByAccount/{account}")
    public Result selectById(@PathVariable String account){
        log.info("依照id: {} 查詢用戶", account);
        User user = userService.selectByAccount(account);
        return Result.success(user);
    }
}
