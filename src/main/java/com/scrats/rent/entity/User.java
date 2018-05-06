package com.scrats.rent.entity;

import lombok.Data;

/**
 * 后台登陆用户实体类
 * Created by lol on 15/4/13.
 */
@Data
public class User extends Common {

    private static final long serialVersionUID = 8587840503268891205L;

    private int id;//主键
    private String username;//用户名
    private String password;//密码
    private int type;//0.超级管理员1.管理员，2.房东，3.收租人，4.租户，5.巡管员，6.游客，
    private String remark;// 备注
    private String name;//姓名
    private String phone;//电话
    private Boolean sex;//性别，true-男 false-女
    private int age;//年龄
    private String pic;//头像
    private String address;//住址

}
