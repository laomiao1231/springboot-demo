package com.m.mq;

import com.m.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MsgReceiver {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void process(Message message, Channel channel) throws IOException {
        System.out.println("接收处理队列当中的消息： " + new String(message.getBody()));
        // 采用手动应答模式, 手动确认应答更为安全稳定
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

}
