package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/13 16:33.
 */
@Data
public class Account extends BaseEntity {

    private int id;//主键
    private String username;//用户名
    private String pwd;//密码
    private String iphone;//手机号码

}
