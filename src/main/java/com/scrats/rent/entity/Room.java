package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;

/**
 * 房间实体类
 * Created by lol on 15/4/23.
 */
@Data
public class Room extends BaseEntity {

    private int id;//主键
    private String roomNo;//房间号
    private int deposit;//押金[分]
    private int rent;//租金[分]
    private int waterLast;//上月水表读数
    private int waterThis;//本月水表读数
    private int powerLast;//上月电表读数
    private int powerThis;//本月电表读数
    private int powerThreeLast;//上月用三电表读数
    private int powerThreeThis;//本月用三电表读数
    private int rentDay;//收租日
    private String desc;//描述
    private String rented;//是否出租,0- 未出租,1-已出租

    private int buildingId;//房子id,一个房间对应一个房子id

}
