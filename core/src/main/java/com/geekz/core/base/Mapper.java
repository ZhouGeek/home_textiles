package com.geekz.core.base;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.delete.DeleteMapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;
import tk.mybatis.mapper.common.base.select.*;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 *
 * Mapper
 *
 * @name Mapper
 * @version 1.0
 * @author zrz 2019-1-15
 */
public interface Mapper<T> extends BaseMapper<T>, ConditionMapper<T>, IdsMapper<T>, InsertListMapper<T>,
        InsertMapper<T>, InsertSelectiveMapper<T>, UpdateByPrimaryKeyMapper<T>, UpdateByPrimaryKeySelectiveMapper<T>,
        DeleteByPrimaryKeyMapper<T>, DeleteMapper<T>, SelectByPrimaryKeyMapper<T>, SelectOneMapper<T>, SelectMapper<T>,
        SelectAllMapper<T>, SelectCountMapper<T> {
}