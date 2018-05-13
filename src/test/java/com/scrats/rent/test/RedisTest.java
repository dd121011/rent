package com.scrats.rent.test;

import com.scrats.rent.entity.Admin;
import com.scrats.rent.util.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with scrat.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/11 15:37.
 * Description: ${DESCRIPTION}.
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class RedisTest extends BaseTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void redisTest() {
        redisUtil.set("test", "tt");
        Object reidsValue = redisUtil.get("test");
        System.out.println(reidsValue);

        Admin admin = new Admin();

        System.out.println(admin.toString());
    }

}
