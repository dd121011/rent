package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/13 16:33.
 */
@Data
@Table
public class Account extends BaseEntity {

    @Column(name = "account_id")
    private int accountId;//主键
    private String username;//用户名
    private String pwd;//密码
    private String phone;//手机号码

}
