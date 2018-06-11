package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

/**
 * 房子实体类,一个房东可能有多个房子，一个房子对应多个房间
 * Created by lol on 15/4/23.
 */
@Data
public class Building extends BaseEntity {

    private static final long serialVersionUID = -8526366061711633128L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buildingId;//主键
    private String facilities;//配套设施id字符串[,隔开]
    private String extraFee;//额外收费项id字符串[,隔开]
    private int rooms;//总的房间数
    private int roomAble;//可用房间数,通过总的房间数和可用房间数可以计算出出租房间数
    private String describe;//描述
    private String address;//地址

    private List<Attachment> attachmentList;//一个房子对应多个attachment


}
