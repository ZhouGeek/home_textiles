package com.geekz.core.system.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.geekz.core.base.BaseDTO;
import com.geekz.core.constants.Constants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * 版权：(C) 版权所有 2000-2016 上海天好电子商务股份有限公司苏州分公司
 * <简述> 维护编码自生长表
 * <详细描述> 维护编码自生长表
 * @author   zrz
 * @version  $Id$
 * @since
 * @see
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "sys_seq_setting")
public class SeqSetting {

    /**
     * 操作类型，add/update/delete 参考：{@link Constants.Operation}
     */
    @Transient
    private String _operate;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy("DESC")
    private Long id;

    /**
     * 流水号（最大值99999）
     */
    private Integer sequence;

    /**
     * 类型
     */
    private String type;

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String get_operate() {
        return _operate;
    }

    public void set_operate(String _operate) {
        this._operate = _operate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
