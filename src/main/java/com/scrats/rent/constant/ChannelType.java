package com.scrats.rent.constant;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/13 23:32.
 */
public enum ChannelType {

    unpay("未支付", "99"),
    wx("微信", "1"),
    alipay("支付宝", "2"),
    cash("现金", "0");

    private String name;

    private String value;

    ChannelType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public static ChannelType fromValue(String value) {
        ChannelType[] channelTypes = ChannelType.values();
        for (ChannelType type : channelTypes) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return null;
    }
}
