package com.example.backdemo.config.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
//@Configuration
//@EnableCaching
public class RedisConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;
    @Value("${spring.redis.password}")
    private String password;

  /*  @Value("${spring.redis.password}")
    private String password;*/

   /* @Value("${spring.redis.database}")
    private int dataBase;*/

    @Bean
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);//有密码
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);//无密码
        logger.info("JedisPool注入成功！！");
        logger.info("redis地址：" + host + ":" + port);
        return jedisPool;
    }
}
