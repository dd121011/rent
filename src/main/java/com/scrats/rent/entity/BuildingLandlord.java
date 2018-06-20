package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;

/**
 * 房子实体类,一个房东可能有多个房子，一个房子对应多个房间
 * Created by lol on 15/4/23.
 */
@Data
public class BuildingLandlord extends BaseEntity {

    private static final long serialVersionUID = 5056011274302988326L;

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer buildingLandlordId;//主键
    private Integer landlordId;//主键
    private Integer buildingId;//主键


    public BuildingLandlord() {
        super();
    }

    public BuildingLandlord(Integer landlordId, Integer buildingId) {
        super();
        this.landlordId = landlordId;
        this.buildingId = buildingId;
    }
}
