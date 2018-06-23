package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/12 16:58.
 */
@Data
public class Extra extends BaseEntity {

    private static final long serialVersionUID = -2526422681008125830L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer extraId;//主键
    private String name;//名称
    private String unit;//单位
    private String code;//编码

    @Transient
    private Integer number;//初始数量
    @Transient
    private Integer price;//单价


}
