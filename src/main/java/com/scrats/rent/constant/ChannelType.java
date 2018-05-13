package com.scrats.rent.constant;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/13 23:32.
 */
public enum ChannelType {

    wx("微信"),
    alipay("支付宝"),
    cash("现金");

    private String name;

    ChannelType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
