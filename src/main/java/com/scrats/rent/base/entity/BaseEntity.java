package com.scrats.rent.base.entity;

import lombok.Data;

@Data
public class BaseEntity {

//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private long createTs;//创建时间
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private long updateTs;//更新时间
    private String remark;//备注
    private long deleteTs;//删除时间戳


}
