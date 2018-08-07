package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/18 09:31.
 */
@Data
public class ExtraHistory extends BaseEntity {

    private static final long serialVersionUID = -6563910655403236128L;

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer extraHistoryId;//主键
    private Integer roomId;//房间Id
    private Integer count;//水表读数, 单位KG
    private String month;//统计月, eg 201805
    private String dicItermCode;//额外收费项code
    private Integer barginExtraId;//额外收费项Id
    private Integer barginId;//合同Id
    private Integer buildingId;//房子Id
    private Integer rentId;//房租Id

    @Transient
    private String value;
    @Transient
    private Integer number;

    public ExtraHistory() {

    }

    public ExtraHistory(Integer roomId, Integer count, String month, String dicItermCode, Integer barginExtraId, Integer barginId, Integer buildingId) {
        this.roomId = roomId;
        this.count = count;
        this.month = month;
        this.dicItermCode = dicItermCode;
        this.barginExtraId = barginExtraId;
        this.barginId = barginId;
        this.buildingId = buildingId;
    }
}
