package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/13 16:33.
 */
@Data
public class Account extends BaseEntity {

    private static final long serialVersionUID = 7219373609398245913L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;//主键
    private String username;//用户名
    private String pwd;//密码
    private String phone;//手机号码

    public Account() {
        super();
    }

    public Account(String username, String pwd) {
        super();
        this.username = username;
        this.pwd = pwd;
    }
}
