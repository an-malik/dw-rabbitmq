package com.weather.ingester.client;

import com.rabbitmq.client.*;
import com.weather.ingester.config.AMQPConfiguration;
import com.weather.ingester.config.ConnectorConfiguration;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

public class AMQPClient {
    private Channel channel;
    private Connection connection;
    private AMQPConfiguration amqpConfiguration;

    public AMQPClient(ConnectorConfiguration connectorConfiguration) throws IOException, TimeoutException {
        this.amqpConfiguration = connectorConfiguration.getAmqpConfiguration();
        initClient();
    }

    //TODO remove this, after we figure out how to
    public AMQPClient() {
        this.amqpConfiguration = new AMQPConfiguration();
        amqpConfiguration.setHost("localhost");
        amqpConfiguration.setPort(4041);
        amqpConfiguration.setQueue("com.weather.sensors");
    }

    private void initClient() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setConnectionTimeout(1000);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost(amqpConfiguration.getHost());
        factory.setPort(amqpConfiguration.getPort());

        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public String readMessage(Consumer consumer) throws IOException {
        return channel.basicConsume(amqpConfiguration.getQueue(), true, consumer);
    }

    public void close() throws IOException, TimeoutException {
        this.channel.close();
        this.connection.close();
    }
}
