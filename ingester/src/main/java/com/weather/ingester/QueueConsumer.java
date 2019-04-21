package com.weather.ingester;

import com.rabbitmq.client.*;
import com.weather.ingester.client.AMQPClient;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QueueConsumer implements Runnable, Consumer {

    AMQPClient amqpClient;

    public QueueConsumer(AMQPClient amqpClient) {
        this.amqpClient = amqpClient;
    }

    public void run() {
        try {
            String data = amqpClient.readMessage(this);
            System.out.println(data + " : hell yeah!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer "+ consumerTag +" registered");
    }

    public void handleDelivery(String consumerTag, Envelope env,
                               AMQP.BasicProperties props, byte[] body) {
        String dataItem = SerializationUtils.deserialize(body);
        System.out.println("Damnn boy!! " + dataItem);
    }

    public void handleCancel(String consumerTag) {}
    public void handleCancelOk(String consumerTag) {}
    public void handleRecoverOk(String consumerTag) {}
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {}
}