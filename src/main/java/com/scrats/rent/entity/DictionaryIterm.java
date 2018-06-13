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
public class DictionaryIterm extends BaseEntity {

    private static final long serialVersionUID = 4656028240726886479L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dicItermId;//主键
    private Integer dicId;//字典类型Id
    private String value;//字典项目值

    public DictionaryIterm() {
    }
}
