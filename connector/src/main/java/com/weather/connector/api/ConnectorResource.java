package com.weather.connector.api;

import com.weather.connector.dto.WeatherRecordDto;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/weather")
@Produces(MediaType.APPLICATION_JSON)
public class ConnectorResource {

    private ConnectorService connectorService;

    public ConnectorResource(ConnectorService connectorService) {
        this.connectorService = connectorService;
    }

    @POST
    public void getWeatherReading(WeatherRecordDto weatherRecordDto) throws IOException {
        connectorService.saveWeatherRecord(weatherRecordDto);
    }

    @POST
    public void testEndpointRemoveLater(String string) throws IOException {
        connectorService.testStringResource(string);
    }
}
