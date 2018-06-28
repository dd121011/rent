package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 押金实体类
 * Created by lol on 15/4/23.
 */
@Data
public class RentIterm extends BaseEntity {

    private static final long serialVersionUID = 1309693000238636845L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentItermId;//主键
    private Integer rentId;//房租Id
    private Integer barginExtraId;//合同额外收费项Id
    private String value;//房租项目名称
    private Integer price;//房租项目单价[分]
    private String unit;//房租项目单位,从合同获得
    private Integer number;//房租项目数量
    private Integer money;//房租项目金额
    private String description;//房租项目描述

}
