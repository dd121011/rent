package com.scrats.rent.constant;

import lombok.Getter;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/13 21:19.
 */
public enum SexType {

    secret("保密", "0"),
    male("男", "1"),
    female("女", "2");

    @Getter
    private String name;
    @Getter
    private String value;


    SexType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static SexType fromValue(String value) {
        SexType[] sexTypes = SexType.values();
        for (SexType type : sexTypes) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return null;
    }

}
