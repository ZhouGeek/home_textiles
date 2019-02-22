package com.geekz.core.system.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.geekz.core.base.BaseController;
import com.geekz.core.base.Result;
import com.geekz.core.constants.BaseEnums;
import com.geekz.core.constants.Constants;
import com.geekz.core.constants.ModelConstant;
import com.geekz.core.system.common.service.SeqSettingService;
import com.geekz.core.system.user.dto.User;
import com.geekz.core.system.user.service.UserService;
import com.geekz.core.util.Results;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
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

    /**
     * seqSettingService
     */
    @Autowired
    private SeqSettingService seqSettingService;

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

    /**
     * 根据openId查询用户
     * @param code
     * @return
     */
    @RequestMapping("/sys/user/queryByOpenId/{code}")
    public Result queryByOpenId(@PathVariable String code, @RequestBody JSONObject obj) throws Exception {
        String AppID = Constants.APP_ID;
        String AppSecret= Constants.App_SECRET;//这两个都可以从微信公众平台中查找
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
                + AppID + "&secret=" + AppSecret + "&js_code="
                + code + "&grant_type=authorization_code";
        URL reqURL = new URL(url);
        HttpsURLConnection openConnection = (HttpsURLConnection) reqURL
                .openConnection();
        openConnection.setConnectTimeout(10000);
        //这里我感觉获取openid的时间比较长，不过也可能是我网络的问题，
        //所以设置的响应时间比较长
        openConnection.connect();
        InputStream in = openConnection.getInputStream();

        StringBuilder builder = new StringBuilder();
        BufferedReader bufreader = new BufferedReader(new InputStreamReader(in));
        for (String temp = bufreader.readLine(); temp != null; temp = bufreader
                .readLine()) {
            builder.append(temp);
        }

        String stringObj = builder.toString();

        //JSONObject
        JSONObject jsonObject=JSONObject.parseObject(stringObj);
        User user = null;

        if (jsonObject != null && !jsonObject.isEmpty()) {
            String openId = jsonObject.getString("openid");
            if (StringUtils.isNotBlank(openId)) {
                user = userService.get("openId", openId);
            }

            if (obj != null) {
                String data = obj.toJSONString();
                //解析json数据
                JSONObject json = JSON.parseObject(data);
                if (json != null && !json.isEmpty()) {
                    if (user != null) {
                        user.set_operate(Constants.Operation.UPDATE);
                        user.setUpdateDate(new Date());
                    } else {
                        user = new User();
                        user.set_operate(Constants.Operation.ADD);
                        user.setCreateDate(new Date());
                        //user.setCard(seqSettingService.doGenerate(ModelConstant.HY_CODE, ModelConstant.HY_CODE));
                    }
                    user.setNickname(json.getString(ModelConstant.NICK_NAME));
                    user.setProvince(json.getString(ModelConstant.PROVINCE));
                    user.setCity(json.getString(ModelConstant.CITY));
                    user.setCountry(json.getString(ModelConstant.COUNTRY));
                    user.setSex(json.getInteger(ModelConstant.SEX));
                    user.setOpenId(openId);
                    user = userService.persistSelective(user);
                }
            }
        }
        in.close();
        openConnection.disconnect();
        return Results.successWithData(user);
    }

}