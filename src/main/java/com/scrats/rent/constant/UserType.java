package com.scrats.rent.constant;

import lombok.Getter;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/6 22:57.
 */
public enum UserType {

    renter("租客", "0"),
    landlord("房东", "1"),
    admin("管理员", "2"),
    guard("巡管员", "3"),
    administrator("超级管理员", "4");

    @Getter
    private String name;
    @Getter
    private String value;


    UserType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static UserType fromValue(String value) {
        UserType[] userTypes = UserType.values();
        for (UserType type : userTypes) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return null;
    }
}
