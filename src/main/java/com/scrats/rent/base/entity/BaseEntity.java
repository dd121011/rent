package com.scrats.rent.base.entity;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -4970953425736091134L;

    //    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "create_ts")
    private long createTs;//创建时间
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "update_ts")
    private long updateTs;//更新时间
    private String remark;//备注
//    @Column(name = "delete_ts")
    private long deleteTs;//删除时间戳


}
