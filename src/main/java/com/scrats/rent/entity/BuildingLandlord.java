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
    private Integer building_landlord_id;//主键
    private Integer landlord_id;//主键
    private Integer building_id;//主键


    public BuildingLandlord() {
        super();
    }

    public BuildingLandlord(Integer landlord_id, Integer building_id) {
        super();
        this.landlord_id = landlord_id;
        this.building_id = building_id;
    }
}
