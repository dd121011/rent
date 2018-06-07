package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import com.scrats.rent.constant.SexType;
import com.scrats.rent.constant.UserType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 用户实体类
 * Created by lol on 15/4/13.
 */
@Data
public class User extends BaseEntity {

    private static final long serialVersionUID = -6043513751393352184L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;//主键
    private String type;//用户类型 , 0-租客, 1-房东, 2-管理员, 3-巡管员, 4-超级管理员
    private String name;//姓名
    private String sex;//0-保密, 1-男, 2-女
    private int age;//年龄
    private String avatar;//头像URL
    private String qq;//qq
    private String wechat;//微信
    private String email;//email
    private String profession;//职业
    private String hometown;//籍贯

    private String address;//地址
    @Column
    private int accountId;//账号Id

    public String getTypeName(){
        return UserType.fromValue(type).getName();
    }

    public String getSexName(){
        return SexType.fromValue(sex).getName();
    }

}
