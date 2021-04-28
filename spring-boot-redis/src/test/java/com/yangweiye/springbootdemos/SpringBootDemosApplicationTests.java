package com.yangweiye.springbootdemos;

import com.yangweiye.springbootdemos.pojo.User;
import com.yangweiye.springbootdemos.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootDemosApplicationTests {
    private static Logger log = LoggerFactory.getLogger(SpringBootDemosApplication.class);

    @Autowired
    ApplicationContext app;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {
        log.info("hello");
    }

    @Test
    public void testRedisConnection() {
        //基本等同于原生操作 十分不友善
        RedisConnection connection = redisConnectionFactory.getConnection();
        connection.set("key1".getBytes(), "value1".getBytes());
    }

    @Test
    public void testRedisTemplate() {
        redisTemplate.opsForValue().set("key2", "value2");
    }

    @Test
    public void testString() {
        stringRedisTemplate.opsForValue().set("k1", "v1");
        String k1 = stringRedisTemplate.opsForValue().get("k1");
        stringRedisTemplate.opsForValue().set("num", "1");
        BoundValueOperations<String, String> num = stringRedisTemplate.boundValueOps("num");
        num.increment(2);
        num.decrement(1);
        log.info(k1);
    }

    @Test
    public void testList() {
        stringRedisTemplate.opsForList().leftPush("l1", "v5");
        stringRedisTemplate.opsForList().leftPushAll("l1", "v4", "v3", "v2", "v1");
        stringRedisTemplate.opsForList().rightPushAll("l1", "v6", "v7", "v8", "v9");
        BoundListOperations<String, String> l1 = stringRedisTemplate.boundListOps("l1");
        List<String> range = l1.range(0, -1);
        range.forEach(System.out::println);
        String left = l1.leftPop();
        String right = l1.rightPop();
        log.info(left + " -- " + right);
        range = l1.range(0, -1);
        range.forEach(System.out::println);
    }

    @Test
    public void testHash() {
        stringRedisTemplate.opsForHash().put("h1", "f1", "v1");
        Map<String, String> map = new HashMap<>();
        map.put("field1", "value1");
        map.put("field2", "value2");
        map.put("field3", "value3");
        BoundHashOperations<String, Object, Object> h1 = stringRedisTemplate.boundHashOps("h1");
        h1.putAll(map);
        Map<Object, Object> entries = h1.entries();
        System.out.println(entries);
        h1.delete("field1", "field2");
        entries = h1.entries();
        System.out.println(entries);
    }

    @Test
    public void testSet() {
        stringRedisTemplate.opsForSet().add("set1", "v1", "v1", "v2", "v3", "v4");
        stringRedisTemplate.opsForSet().add("set2", "v2", "v4", "v6");
        BoundSetOperations<String, String> set1 = stringRedisTemplate.boundSetOps("set1");
        Set<String> members = set1.members();
        System.out.println(members);
        Set<String> intersect = set1.intersect("set2");
        System.out.println(intersect);
        HashSet<String> s = new HashSet<>();
        s.add("v1");
        s.add("v2");
        set1.diffAndStore(s, "ssdiff");
        Set<String> set2 = set1.union("set2");
        System.out.println(set2);
    }

    @Test
    public void testZSet() {
        stringRedisTemplate.opsForZSet().add("zset1", "zset1", 0.5);

        DefaultTypedTuple<String> tuple1 = new DefaultTypedTuple<>("z1", 1.0);
        DefaultTypedTuple<String> tuple2 = new DefaultTypedTuple<>("z2", 2.0);
        DefaultTypedTuple<String> tuple3 = new DefaultTypedTuple<>("z3", 3.0);
        DefaultTypedTuple<String> tuple4 = new DefaultTypedTuple<>("z4", 4.0);
        Set<ZSetOperations.TypedTuple<String>> set = new HashSet<>();
        set.add(tuple1);
        set.add(tuple2);
        set.add(tuple3);
        set.add(tuple4);
        stringRedisTemplate.opsForZSet().add("zset1", set);
        BoundZSetOperations<String, String> zset1 = stringRedisTemplate.boundZSetOps("zset1");
        Set<String> range1 = zset1.range(0, -1);
        System.out.println(range1);
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.lte("z4");
        range.gt("z2");
        Set<String> strings = zset1.rangeByLex(range);
        System.out.println(strings);
        Set<String> strings1 = zset1.rangeByScore(1, 4);
        System.out.println(strings1);
    }

    @Test
    public void testRedisTransactional() {
        Object execute = stringRedisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.watch("k1");
                redisOperations.multi();
                redisOperations.opsForValue().set("kk1", "vv1");
                Object kk1 = redisOperations.opsForValue().get("kk1");
                System.out.println("拿不到的 事物还没执行 -- " + kk1);
                redisOperations.opsForValue().set("kk2", "vv2");
                redisOperations.exec();
                return null;
            }
        });
    }

    @Test
    public void testRedisPipeline() {
        //pipeline 可以集中发送redis命令 能够节省很多时间
        List<Object> objects = stringRedisTemplate.executePipelined(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                for (int i = 0; i < 10000; i++) {
                    redisOperations.opsForValue().set("p_" + i, "pv_" + i);
                    redisOperations.opsForValue().get("p_" + i);
                }
                return null;
            }
        });
    }

    @Test
    public void testRedisListener() {
        stringRedisTemplate.convertAndSend("channel1", "message");
    }

    // redis 还可以写 lua 脚本这里我没有写 sorry
    // 后来我又写了

    @Test
    public void testLua() {
        DefaultRedisScript<String> stringDefaultRedisScript = new DefaultRedisScript<>();
        //这是lua 脚本
        stringDefaultRedisScript.setScriptText("return 'Hello redis Lua'");
        //设置返回类型
        stringDefaultRedisScript.setResultType(String.class);
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        String result = (String) redisTemplate.execute(stringDefaultRedisScript, stringSerializer, stringSerializer, null);

        System.out.println(result);
    }

    @Test
    public void testLuaParam() {
        //定义Lua脚本
        String lua = "redis.call ('set',KEYS[1], ARGV[1]) \n"
                + "redis.call('set',KEYS[2], ARGV[2]) \n"
                + "local str1 = redis.call ('get',KEYS [1]) \n"
                + "local str2 = redis.call ('get',KEYS [2]) \n"
                + "if str1 == str2 then \n"
                + "return 1 \n"
                + "end \n"
                + "return 0 \n";
        System.out.println(lua);

        //结果返回Long
        DefaultRedisScript<Long> rs = new DefaultRedisScript<Long>();
        rs.setScriptText(lua);
        rs.setResultType(Long.class);
        //采用字符串序列化器
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        //定义key参数
        List<String> keyList = new ArrayList<>();

        String key1 = "kkk1";
        String key2 = "kkk2";
        String value1 = "vvv1";
        String value2 = "vvv1";

        keyList.add(key1);
        keyList.add(key2);
        //传递两个参数值，其中第一个序列化器是key的序列化器，第二个序列化器是参数的序列化器
        Long result = (Long) redisTemplate.execute(rs, stringSerializer, stringSerializer, keyList, value1, value2);

        System.out.println(result);
    }

    @Autowired
    private UserService userService;

    @Test
    public void testRedisCache() {
//        User user = new User();
//        user.setUserName("redis_cache");
//        user.setSex(1);
//        user.setNote("nono");
//        userService.insertUser(user);
//        System.out.println(user);

        //userService.deleteUser(42L);

        User user = userService.getUser(43L);
        System.out.println(user);
    }
}
