package com.scrats.rent.constant;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/13 21:19.
 */
public enum SexType {

    secret("保密"),
    male("男"),
    female("女");

    private String name;

    SexType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
