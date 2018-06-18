package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/23 23:37.
 */
@Data
public class Attachment extends BaseEntity {

    private static final long serialVersionUID = -2817612996471911838L;

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer attachmentId;//主键
    private String store;//存放地址
}
