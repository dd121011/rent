package com.scrats.rent.common.annotation;

import java.lang.annotation.*;

/**
 * @Created with scrat.
 * @Description: 在参数添加@CurrentUser User user来获取当前登录用户.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/8 17:11.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
}
