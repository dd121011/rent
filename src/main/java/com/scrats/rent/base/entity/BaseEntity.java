package com.scrats.rent.base.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -4970953425736091134L;

    //    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Long createTs;//创建时间
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Long updateTs;//更新时间
    private String remark;//备注
    private Long deleteTs;//删除时间戳

}
