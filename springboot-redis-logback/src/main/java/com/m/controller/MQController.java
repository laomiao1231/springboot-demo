package com.m.controller;

import com.m.mq.MsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MQController {

    @Autowired
    private MsgProducer msgProducer;

    @GetMapping("/rabbit")
    public void send() {
        msgProducer.sendMsg();
    }
}
