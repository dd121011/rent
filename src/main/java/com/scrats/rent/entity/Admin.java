package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;

/**
 * 系统管理员实体类
 * Created by lol on 15/4/13.
 */
@Data
public class Admin extends BaseEntity{

    private static final long serialVersionUID = 4011410956895224111L;

    private int id;//主键
    private String jobNo;//工号
    private String administrator;//是否超级管理员, 0-否, 1-是

    private int userId;//一个管理员对应一个账号

}
