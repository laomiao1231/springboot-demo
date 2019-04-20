package com.m.controller;

import com.m.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreadPoolController {
    @Autowired
    AsyncService asyncService;

    @GetMapping("/thread")
    public String thread() {
        asyncService.ExecuteAsync();
        System.out.println("thread pool");
        return "thread";
    }
}
