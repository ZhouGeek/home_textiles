package com.geekz.core.constants;

import com.google.common.base.Charsets;

import java.nio.charset.Charset;

/**
 * 系统级常量类
 *
 * @version 1.0
 * @author zrz 2019-1-12
 */
public class Constants {

    public static final String APP_NAME = "home_textiles";

    /**
     * 系统编码
     */
    public static final Charset CHARSET = Charsets.UTF_8;

    /**
     * 标识：是/否、启用/禁用等
     */
    public interface Flag {

        Integer YES = 1;

        Integer NO = 0;
    }

    /**
     * 操作类型
     */
    public interface Operation {
        /**
         * 添加
         */
        String ADD = "add";
        /**
         * 更新
         */
        String UPDATE = "update";
        /**
         * 删除
         */
        String DELETE = "delete";
    }
}