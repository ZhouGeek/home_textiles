package com.geekz.core.base;

/**
 * 基础枚举接口
 *
 * @version 1.0
 * Created by zrz on 2019-1-12.
 */
public interface BaseEnum<K, V> {

    /**
     * 获取编码
     *
     * @return 编码
     */
    K code();

    /**
     * 获取描述
     *
     * @return 描述
     */
    V desc();

}
