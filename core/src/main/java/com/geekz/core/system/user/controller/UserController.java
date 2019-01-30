package com.geekz.core.system.user.controller;

import com.geekz.core.base.BaseController;
import com.geekz.core.base.Result;
import com.geekz.core.constants.BaseEnums;
import com.geekz.core.system.user.dto.User;
import com.geekz.core.system.user.service.UserService;
import com.geekz.core.util.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户Controller
 *
 * @version 1.0
 * @author bojiangzhou 2017-12-31
 */
@RequestMapping
@RestController
public class UserController extends BaseController {

    /**
     * 可以看到引入的包是slf4j.Logger，代码里并没有引用任何一个跟 Logback 相关的类，这便是使用 Slf4j的好处，在需要将日志框架切换为其它日志框架时，无需改动已有的代码。
     * LoggerFactory 的 getLogger() 方法接收一个参数，以这个参数决定 logger 的名字，比如第二图中的日志输出。在为 logger 命名时，用类的全限定类名作为 logger name 是最好的策略，这样能够追踪到每一条日志消息的来源
     * 可以看到，可以通过提供占位符，以参数化的方式打印日志，避免字符串拼接的不必要损耗，也无需通过logger.isDebugEnabled()这种方式判断是否需要打印。
     */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * userService
     */
    @Autowired
    private UserService userService;


    @PostMapping("/sys/user/queryAll")
    public Result queryAll(){
        List<User> list = userService.selectAll();
        return Results.successWithData(list, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    @RequestMapping("/sys/user/queryOne/{userId}")
    public Result queryOne(@PathVariable Long userId){
        User user = userService.get(userId);

        logger.debug("userId:{},userName:{},birthday:{}", user.getUserId(), user.getUsername(), user.getBirthday());
        logger.info("userId:{},userName:{},birthday:{}", user.getUserId(), user.getUsername(), user.getBirthday());
        logger.error("userId:{},userName:{},birthday:{}", user.getUserId(), user.getUsername(), user.getBirthday());

        return Results.successWithData(user);
    }

    @PostMapping("/sys/user/save")
    public Result save(@Valid @RequestBody User user){
        user = userService.insertSelective(user);
        return Results.successWithData(user);
    }

    @PostMapping("/sys/user/update")
    public Result update(@Valid @RequestBody List<User> user){
        user = userService.persistSelective(user);
        return Results.successWithData(user);
    }

    @RequestMapping("/sys/user/delete")
    public Result delete(User user){
        userService.delete(user);
        return Results.success();
    }

    @RequestMapping("/sys/user/delete/{userId}")
    public Result delete(@PathVariable Long userId){
        userService.delete(userId);
        return Results.success();
    }

}