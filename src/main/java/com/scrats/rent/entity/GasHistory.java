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
 * @Date: 2018/6/18 09:31.
 */
@Data
public class GasHistory extends BaseEntity {

    private static final long serialVersionUID = -6046288070660937109L;

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer gasHistoryId;//主键
    private Integer roomId;//房间Id
    private Integer buildingId;//房子Id
    private Integer count;//天然气读数, 单位m2
    private String month;//统计月, eg 201805
}
