package com.scrats.rent.entity;

import lombok.Data;

/**
 * 房东实体类
 * Created by lol on 15/4/23.
 */
@Data
public class Landlord extends Common {

    private static final long serialVersionUID = -5858619830157696558L;

    private int id;//主键
    private int buildingNo;//房子数量
    private String address;//

    private int userId;//一个房东对应一个账号

}
