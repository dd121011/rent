package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;

/**
 * 用户实体类
 * Created by lol on 15/4/13.
 */
@Data
public class User extends BaseEntity {

    private int id;//主键
    private String type;//用户类型 , 0-租客, 1-房东, 2-管理员, 3-巡管员
    private String name;//姓名
    private Boolean sex;//0-保密, 1-男, 2-女
    private int age;//年龄
    private String pic;//头像
    private String qq;//qq
    private String wechat;//微信
    private String email;//email
    private String profession;//职业
    private String hometown;//籍贯

    private int addressId;//地址Id
    private int accountId;//账号Id


}
