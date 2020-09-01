package com.atguigu.jedis.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

import java.util.List;

public class AtguiguJedisTest {

    @Test
    public void testJedisPool() {

        // 1.准备连接信息：主机地址和端口号
        String host = "192.168.109.100";
        int port = Protocol.DEFAULT_PORT;

        // 2.创建JedisPool对象
        JedisPool jedisPool = new JedisPool(host, port);

        // 3.获取Jedis对象
        Jedis jedis = jedisPool.getResource();

        // 4.调用方法执行对应的操作
        List<String> fruitList =
                jedis.lrange("fruit", 0, -1);

        for (String fruit : fruitList) {
            System.out.println("fruit="+fruit);
        }

        // 5.关闭连接
        jedis.close();

    }

    @Test
    public void testJedis() {

        // 1.准备连接信息：主机地址和端口号
        String host = "192.168.109.100";
        int port = Protocol.DEFAULT_PORT;

        // 2.创建Jedis对象
        Jedis jedis = new Jedis(host, port);

        // 3.调用Jedis对象的方法远程操作Redis服务器
        String ping = jedis.ping();
        System.out.println("ping="+ping);

        // 4.Redis命令和对应的方法同名，按照需要调用对应的方法即可
        Long result = jedis.lpush("fruit", "apple", "banana", "orange");

        System.out.println("lpush result="+result);

        // 关闭连接
        jedis.close();
    }

}
