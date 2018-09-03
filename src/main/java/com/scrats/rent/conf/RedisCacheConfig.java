package com.scrats.rent.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description:
 * @User: lol.
 * @Date: 2018/1/9 15:16.
 */
//@Configuration
//@EnableCaching//启用缓存
//@EnableConfigurationProperties(RedisProperties.class)
public class RedisCacheConfig extends CachingConfigurerSupport {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public JedisPool redisPoolFactory() {
        logger.info("JedisPool注入成功！！");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisProperties.getPool().getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getPool().getMaxWait());

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisProperties.getHost(), redisProperties.getPort(), redisProperties.getTimeout(), redisProperties.getPassword());

        return jedisPool;
    }

    /**
     * redis模板操作类,类似于jdbcTemplate的一个类;
     *
     * 虽然CacheManager也能获取到Cache对象，但是操作起来没有那么灵活；
     *
     * 这里在扩展下：RedisTemplate这个类不见得很好操作，我们可以在进行扩展一个我们
     *
     * 自己的缓存类，比如：RedisStorage类;
     *
     * @param factory : 通过Spring进行注入，参数在application.properties进行配置；
     * @return
     */
    @Bean
    public RedisTemplate<String, ?> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, ?> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

    /***
     * 缓存管理器
     * @param redisTemplate
     * @return
     */

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

        //默认超时时间,单位秒
        cacheManager.setDefaultExpiration(600);
        //根据缓存名称设置超时时间,0为不超时
        Map<String,Long> expires = new ConcurrentHashMap<>();
        cacheManager.setExpires(expires);

        return cacheManager;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getName());
                sb.append(method.getName());
                for(Object obj : objects){
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
}
