package com.scrats.rent.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Created with scrat.
 * @Email: 262297088@qq.com
 * @Description:
 * @User: lol.
 * @Date: 2018/4/16 16:48.
 */
@Data
public class Common implements Serializable{

    private static final long serialVersionUID = -4784471328648543658L;

    private Date createTime;// 创建时间
    private int createId;//创建人
    private Date modifyTime;// 修改时间
    private int modifyId;//修改人
    private String remark;// 备注

}
