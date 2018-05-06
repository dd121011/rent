package com.scrats.rent.entity;

import lombok.Data;

/**
 * 系统管理员实体类
 * Created by lol on 15/4/13.
 */
@Data
public class Admin extends Common{

    private static final long serialVersionUID = 7604085747555126591L;

    private int id;//主键
    private String jobNum;//工号
    private Boolean issuper;//是否超级管理员
    private int userId;//对应user的id

}
