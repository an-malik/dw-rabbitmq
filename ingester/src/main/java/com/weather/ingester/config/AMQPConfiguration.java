package com.weather.ingester.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class AMQPConfiguration {

    @JsonProperty
    private String exchange = "app.weather.data";

    @JsonProperty
    private String queue = "app.weather.sensors";

    @JsonProperty
    private String routingKey = queue;

    @NotEmpty
    @JsonProperty
    private String host;

    @Min(1)
    @Max(65535)
    @JsonProperty
    private int port = ConnectionFactory.DEFAULT_AMQP_PORT;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}