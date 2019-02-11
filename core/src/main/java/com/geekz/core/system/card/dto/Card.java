package com.geekz.core.system.card.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.geekz.core.base.BaseDTO;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * 用户会员卡
 *
 * @version 1.0
 * @author zrz 2019-2-11
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "user_card")
public class Card extends BaseDTO {

    private static final long serialVersionUID = -7395431342743009038L;

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy("DESC")
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 会员卡号
     */
    private String card;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
