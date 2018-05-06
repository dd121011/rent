package com.scrats.rent;

import com.scrats.rent.entity.Admin;
import com.scrats.rent.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentApplicationTests {

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
