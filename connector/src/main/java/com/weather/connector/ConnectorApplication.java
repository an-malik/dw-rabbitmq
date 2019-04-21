package com.weather.connector;

import com.weather.connector.client.AMQPClient;
import com.weather.connector.config.ConnectorConfiguration;
import com.weather.connector.api.ConnectorResource;
import com.weather.connector.api.ConnectorService;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectorApplication extends Application<ConnectorConfiguration> {

    public static void main(String[] args) throws Exception {
        final String command = args.length > 0 ? args[0] : "server";
        final String configFile = args.length > 1 ? args[1] : "connector/application.yml";
        new ConnectorApplication().run(command, configFile);
    }

    @Override
    public String getName() {
        return "weather-service";
    }

    @Override
    public void run(ConnectorConfiguration configuration, Environment environment) throws IOException, TimeoutException {
        AMQPClient amqpClient = new AMQPClient(configuration);
        ConnectorService connectorService = new ConnectorService(amqpClient);
        environment.jersey().register(new ConnectorResource(connectorService));
    }
}

