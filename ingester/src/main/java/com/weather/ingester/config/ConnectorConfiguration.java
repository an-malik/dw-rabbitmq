package com.weather.ingester.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class ConnectorConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty("amqp")
    private AMQPConfiguration amqpConfiguration = new AMQPConfiguration();

    public AMQPConfiguration getAmqpConfiguration() {
        return amqpConfiguration;
    }
}

