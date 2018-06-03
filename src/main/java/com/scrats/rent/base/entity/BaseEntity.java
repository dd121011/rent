package com.scrats.rent.base.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -4970953425736091134L;

    //    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column
    private long createTs;//创建时间
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column
    private long updateTs;//更新时间
    private String remark;//备注
    @Column
    private long deleteTs;//删除时间戳

    @Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;

}
