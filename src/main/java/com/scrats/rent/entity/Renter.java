package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;

/**
 * 租客实体类
 * Created by lol on 15/4/23.
 */
@Data
public class Renter extends BaseEntity {

    private int renterId;//主键
    private String idCard;//identification card 身份证号
    private String idCardPic;//身份证正面
    private String idCardPicBack;//身份证反面

    private int roomid;//房间id,一个租户对应一个房间，一个房间对应多个租户
    private int userId;//一个租客对应一个账号

}
