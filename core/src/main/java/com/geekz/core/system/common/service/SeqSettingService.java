package com.geekz.core.system.common.service;

import com.geekz.core.base.Service;
import com.geekz.core.system.common.dto.SeqSetting;


/**
 * 版权：(C) 版权所有 2000-2014
 * <简述> 设置流水号
 * <详细描述>
 * @author   zrz
 * @version  $Id$
 * @since
 * @see
 */
public interface SeqSettingService extends Service<SeqSetting> {

    /**
     * 生成流水号
     * @param type String 类型
     * @param prefix String 前缀
     * @return 流水号
     */
    public String doGenerate(String type, String prefix);
}