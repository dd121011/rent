package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/23 23:37.
 */
@Data
public class Attachment extends BaseEntity {

    private int attachment_id;//主键
    private String store;//存放地址
}
