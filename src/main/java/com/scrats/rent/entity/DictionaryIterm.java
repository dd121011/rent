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
    private String dicItermCode;//编码
    private String dicCode;//字典类型Id
    private String value;//字典项目值
    private String unit;//单位

}
