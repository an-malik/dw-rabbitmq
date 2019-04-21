package com.weather.ingester;

import com.weather.ingester.client.AMQPClient;
import com.weather.ingester.config.ConnectorConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class IngesterApplication extends Application<ConnectorConfiguration> {

    public static void main(String[] args) throws Exception {
        final String command = args.length > 0 ? args[0] : "server";
        final String configFile = args.length > 1 ? args[1] : "ingester/application.yml";
        new IngesterApplication().run(command, configFile);

//        AMQPClient amqpClient = new AMQPClient();
//        QueueConsumer queueConsumer = new QueueConsumer(amqpClient);
//        Thread consumerThread = new Thread(queueConsumer);
//        consumerThread.start();
    }

    @Override
    public String getName() {
        return "weather-service";
    }

    @Override
    public void run(ConnectorConfiguration configuration, Environment environment) throws IOException, TimeoutException {
        AMQPClient amqpClient = new AMQPClient(configuration);
        QueueConsumer queueConsumer = new QueueConsumer(amqpClient);
        Thread consumerThread = new Thread(queueConsumer);
        consumerThread.start();
    }
}

