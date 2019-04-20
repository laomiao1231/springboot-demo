package com.m.service.impl;

import com.m.service.SecKillService;
import com.m.utils.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SecKillServiceImpl implements SecKillService {
    private static final int TIMEOUT = 10;

    @Autowired
    RedisLock redisLock;
    static Map<String,Integer> products;
    static Map<String,Integer> stock;
    static Map<String,String> orders;
    static {
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("1213", 100000);
        stock.put("1213", 100000);
    }

    private String queryMap(String productId) {
        return "皮蛋粥特价,限量" + products.get(productId)
                + "份,还剩" + stock.get(productId)
                + "份,该商品成功下单" + orders.size() + "人";
    }

    @Override
    public void orderProductMockDiffUser(String productId) {
        long timeValue = System.currentTimeMillis() + TIMEOUT;
        //加锁
        if(!redisLock.lock(productId, String.valueOf(timeValue))) {
            System.out.println("稍等....");
            return;
        }
        int stockNum = stock.get(productId);
        orders.put(UUID.randomUUID().toString(), productId);
        stockNum = stockNum - 1;
        stock.put(productId, stockNum);
        //解锁
        redisLock.unLock(productId, String.valueOf(timeValue));
    }

    @Override
    public String querySecKillProductInfo(String productId) {
        return this.queryMap(productId);
    }
}
