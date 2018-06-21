package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/11 21:25.
 */
@Data
public class Dictionary extends BaseEntity {

    private static final long serialVersionUID = 3519803533413277196L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dicId;//主键
    private String name;//字典类型名称
    private String code;//字典类型编号

}
