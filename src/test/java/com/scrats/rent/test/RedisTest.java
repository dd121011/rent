package com.scrats.rent.test;

import com.scrats.rent.entity.Admin;
import com.scrats.rent.base.service.RedisService;
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
    private RedisService redisService;

    @Test
    public void redisTest() {
        redisService.set("test", "tt");
        Object reidsValue = redisService.get("test");
        System.out.println(reidsValue);

        Admin admin = new Admin();

        System.out.println(admin.toString());
    }

}
