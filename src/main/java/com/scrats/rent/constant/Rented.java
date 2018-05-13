package com.scrats.rent.constant;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/13 22:43.
 */
public enum Rented {
    no("未出租"),yes("已出租");

    private String name;

    Rented(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
