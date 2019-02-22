package com.geekz.core.system.user.controller;

import com.geekz.core.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户Controller
 *
 * @version 1.0
 * @author bojiangzhou 2017-12-31
 */
@RequestMapping("/user")
@Controller
public class PCUserController extends BaseController {

    /**
     * 用户列表
     * @return 用户列表
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(value = "name", defaultValue = "springboot-thymeleaf") String name) {
        request.setAttribute("name", name);
        return "user/list";
    }
}
