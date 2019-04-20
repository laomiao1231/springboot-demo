package com.m;

import redis.clients.jedis.Jedis;

public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("118.24.127.60", 6379);
        jedis.set("k1", "v1");
        jedis.set("k2", "v2");
        System.out.println(jedis.get("k1"));
        jedis.quit();
        System.out.println(jedis.get("k1"));
        System.out.println(jedis);
    }
}
