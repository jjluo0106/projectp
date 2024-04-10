package com.heima.mapper;
import com.heima.pojo.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    public void userInsert(User user);

    int userDelete(List<Integer> ids);

    void userUpdate(User user);

    List<User> userSelectAll();

    User selectByAccount(String account);
}
