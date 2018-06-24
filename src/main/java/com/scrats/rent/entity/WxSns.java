package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/18 09:04.
 */
@Data
public class WxSns extends BaseEntity {

    private static final long serialVersionUID = 3276063863511239753L;

    @Id
    private String openid;//sns openid
    private String unionid;//sns unionid
    private Integer userId;//userId

    @Transient
    private String session_key;
}
