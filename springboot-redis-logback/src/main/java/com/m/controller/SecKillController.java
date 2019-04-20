package com.m.controller;

import com.m.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecKillController {
    @Autowired
    SecKillService secKillService;

    @GetMapping("/mockDiffUser")
    public String orderMockDiffUser() {
        String productId = "1213";
         secKillService.orderProductMockDiffUser(productId);
        return secKillService.querySecKillProductInfo(productId);
    }
}
