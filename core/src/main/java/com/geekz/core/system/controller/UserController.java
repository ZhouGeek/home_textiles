package com.geekz.core.system.controller;

import com.geekz.core.constants.Constants;
import com.geekz.core.base.BaseController;
import com.geekz.core.constants.BaseEnums;
import com.geekz.core.base.Result;
import com.geekz.core.system.dto.User;
import com.geekz.core.util.Dates;
import com.geekz.core.util.Results;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户Controller
 *
 * @version 1.0
 * @author zrz 2019-1-12
 */
@RequestMapping("/sys/user")
@RestController
public class UserController extends BaseController {

    private static List<User> userList = new ArrayList<>();

    // 先静态模拟数据
    static {
        User user1 = new User();
        user1.setUserId(1L);
        user1.setUsername("lufei");
        user1.setNickname("蒙奇D路飞");
        user1.setBirthday(Dates.parseDate("2000-05-05"));
        user1.setEnabled(Constants.Flag.YES);
        userList.add(user1);

        User user2 = new User();
        user2.setUserId(2L);
        user2.setUsername("nami");
        user2.setNickname("娜美");
        user2.setBirthday(Dates.parseDate("2000/7/3"));
        user2.setEnabled(Constants.Flag.YES);
        userList.add(user2);
    }

    @RequestMapping("/queryAll")
    public Result queryAll(){
        return Results.successWithData(userList, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    @RequestMapping("/queryOne/{userId}")
    public Result queryOne(@PathVariable Long userId){
        User user = null;
        for(User u : userList){
            if(u.getUserId().longValue() == userId){
                user = u;
            }
        }
        return Results.successWithData(user);
    }
}