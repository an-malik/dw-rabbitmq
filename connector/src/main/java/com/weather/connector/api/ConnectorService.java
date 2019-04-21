package com.weather.connector.api;

import com.weather.connector.client.AMQPClient;
import com.weather.connector.dto.WeatherRecordDto;

import java.io.IOException;

public class ConnectorService {
    private AMQPClient amqpClient;

    public ConnectorService(AMQPClient amqpClient) {
        this.amqpClient = amqpClient;
    }

    public void saveWeatherRecord(WeatherRecordDto weatherRecordDto) throws IOException {
        // more processing if required
        // validation
        amqpClient.sendMessage(weatherRecordDto);
    }

    public void testStringResource(String record) throws IOException {
        amqpClient.sendMessage(record);
    }
}
