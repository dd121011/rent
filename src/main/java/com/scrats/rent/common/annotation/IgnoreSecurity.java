package com.scrats.rent.common.annotation;


import java.lang.annotation.*;

/**
 * @Created with scrat.
 * @Description: 在方法上面添加@IgnoreSecurity注解表示不对这个接口进行验证.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/8 17:03.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreSecurity {
}
