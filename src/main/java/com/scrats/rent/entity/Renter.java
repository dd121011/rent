package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;

/**
 * 租客实体类
 * Created by lol on 15/4/23.
 */
@Data
public class Renter extends BaseEntity {

    private static final long serialVersionUID = -8072135200688142869L;

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer renterId;//主键
    private String idCard;//identification card 身份证号
    private String idCardPic;//身份证正面
    private String idCardPicBack;//身份证反面

    private Integer roomid;//房间id,一个租户对应一个房间，一个房间对应多个租户
    private Integer buildingId;//房子Id
    private Integer userId;//一个租客对应一个账号

}
