package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

/**
 * 房间实体类
 * Created by lol on 15/4/23.
 */
@Data
public class Room extends BaseEntity {

    private static final long serialVersionUID = 8142937416927930896L;

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer roomId;//主键

    private String roomNo;//房间号
    private Integer living;//厅数量
    private Integer bedroom;//房间数量
    private Integer toilet;//卫生间数量
    private String orientation;//房间朝向
    private String decoration;//装修情况
    private Integer guaranty;//押金月份
    private Integer pay;//租金月份
    private Integer rentFee;//租金[分/月]
    private Integer area;//使用面积[平方分米]
    private String description;//描述
    private String facilities;//配套设施id字符串[,隔开]
    private String extraFee;//额外收费项id字符串[,隔开]
    private String deposits;//押金项id字符串[,隔开]
    private Long rentTs;//是否出租,0- 未出租,1-已出租

    private Integer buildingId;//房子id,一个房间对应一个房子id

    private List<Attachment> attachmentList;//一个房间对应多个attachment

    @Transient
    private String orientationName;
    @Transient
    private String decorationName;
    @Transient
    private List<DictionaryIterm> facilitiesIterm;
    @Transient
    private List<DictionaryIterm> extraFeeIterm;
    @Transient
    private List<DictionaryIterm> depositIterm;
    @Transient
    private List<String> facilityIds;
    @Transient
    private List<String> extraIds;
    @Transient
    private List<String> depositIds;
    @Transient
    private Building building;
    @Transient
    private List<Bargin> barginList;
    @Transient
    private List<Rent> rentList;

}
