package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/18 09:04.
 */
@Data
public class RoomAttach extends BaseEntity {

    private static final long serialVersionUID = 5785468796010400313L;

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer roomAttachId;//主键
    private Integer roomId;//房间Id
    private Integer attachId;//附件Id
}
