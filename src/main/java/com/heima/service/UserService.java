package com.heima.service;

import com.heima.mapper.UserMapper;
import com.heima.pojo.Emp;
import com.heima.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void UserInsert(User user) {
        userMapper.userInsert(user);
    }

    public List<User> userSelectAll(){
        return userMapper.userSelectAll();
    }

    public int userDelete(List<Integer> ids) {
        return userMapper.userDelete(ids);
    }

    public void userUpdate(User user) {
        userMapper.userUpdate(user);
    }

    public User selectByAccount(String account) {
        return userMapper.selectByAccount(account);
    }
}
