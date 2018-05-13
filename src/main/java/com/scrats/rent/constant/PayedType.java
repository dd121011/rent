package com.scrats.rent.constant;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/13 23:33.
 */
public enum PayedType {

    no("未支付"),yes("已支付");

    private String name;

    PayedType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
