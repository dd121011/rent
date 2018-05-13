package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;

/**
 * Created with scrat.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/13 16:00.
 * Description: ${DESCRIPTION}.
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Data
public class Address extends BaseEntity {

    private int    id;//主键
    private String province;//省
    private String city;//市
    private String country;//县
    private String town;//镇
    private String street;//街道
    private String village;//村
    private String district;//小区
    private String buildingNo;//楼牌号
}
