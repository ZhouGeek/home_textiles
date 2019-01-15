package com.geekz.core.system.service.impl;

import org.springframework.stereotype.Service;

import com.geekz.core.base.BaseService;
import com.geekz.core.system.dto.User;
import com.geekz.core.system.service.UserService;

/**
 * 用户Service实现类
 *
 * @version 1.0
 * @author bojiangzhou 2018-01-06
 */
@Service
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Override
    public int delete(String ids) {
        return 0;
    }
}