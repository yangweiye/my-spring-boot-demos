package com.yangweiye.springbootdemos;

import com.yangweiye.springbootdemos.listener.FirstListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.time.Duration;

@SpringBootApplication
@MapperScan(annotationClass = Repository.class)
@EnableCaching
public class SpringBootDemosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemosApplication.class, args);
    }

    //手动配置 RedisConnectionFactory
    //@Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxWaitMillis(2000);
        jedisPoolConfig.setMaxTotal(5);
        jedisPoolConfig.setMaxIdle(3);
        jedisPoolConfig.setMinIdle(1);

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        RedisStandaloneConfiguration standaloneConfiguration = jedisConnectionFactory.getStandaloneConfiguration();
        standaloneConfiguration.setHostName("yangweiye.cn");
        standaloneConfiguration.setPort(6379);
        standaloneConfiguration.setPassword("1998yang");

        return jedisConnectionFactory;
    }

    /*@Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(3);
        jedisPoolConfig.setMinIdle(1);
        jedisPoolConfig.setMaxTotal(5);
        jedisPoolConfig.setMaxWaitMillis(200);

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("yangweiye.cn");
        redisStandaloneConfiguration.setPort(6379);
        redisStandaloneConfiguration.setPassword("1998yang");


        //获得默认的连接池构造
        //这里需要注意的是，JedisConnectionFactory对于Standalone模式的没有（RedisStandaloneConfiguration，JedisPoolConfig）的构造函数，对此
        //我们用JedisClientConfiguration接口的builder方法实例化一个构造器，还得类型转换
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcf = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        //修改我们的连接池配置
        jpcf.poolConfig(jedisPoolConfig);
        //通过构造器来构造jedis客户端配置
        JedisClientConfiguration jedisClientConfiguration = jpcf.build();

        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);

    }*/

    //手动配置RedisTemplate
    //@Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);

        return redisTemplate;
    }

    // 我不清楚 方法A 和 方法B 那个好 有机会请教一下大神吧
    //方法A
    /*@Bean
    public RedisTemplate SetRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);

        return redisTemplate;
    }*/

    @PostConstruct
    public void init() {
        initRedisTemplate();
    }

    //方法B
    @Autowired
    private RedisTemplate redisTemplate;

    private void initRedisTemplate() {
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }


    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(10);
        return threadPoolTaskScheduler;
    }

    //redis 订阅发布
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory, ThreadPoolTaskScheduler threadPoolTaskScheduler, FirstListener firstListener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.setTaskExecutor(threadPoolTaskScheduler);

        ChannelTopic channel1 = new ChannelTopic("channel1");
        container.addMessageListener(firstListener, channel1);

        return container;
    }

    //注意 这里的config 要用链式编程
    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory);

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()));
        config = config.disableKeyPrefix();
        config = config.entryTtl(Duration.ofMinutes(10));
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisCacheWriter, config);
        return redisCacheManager;
    }
}
