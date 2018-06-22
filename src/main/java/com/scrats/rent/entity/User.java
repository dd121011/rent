package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import com.scrats.rent.constant.SexType;
import com.scrats.rent.constant.UserType;
import lombok.Data;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;//主键
    private String type;//用户类型 , 0-租客, 1-房东, 2-管理员, 3-巡管员, 4-超级管理员
    private String name;//姓名
    private String sex;//0-保密, 1-男, 2-女
    private Integer age;//年龄
    private String avatar;//头像URL
    private String qq;//qq
    private String wechat;//微信
    private String email;//email
    private String profession;//职业
    private String hometown;//籍贯
    private String address;//地址
    private Integer accountId;//账号Id

    public User() {
        super();
    }

    public User(String type) {
        super();
        this.type = type;
    }

    public String getTypeName(){
        return UserType.fromValue(type).getName();
    }

    public String getSexName(){
        return SexType.fromValue(sex).getName();
    }

}
