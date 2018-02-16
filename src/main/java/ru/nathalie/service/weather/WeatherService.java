package ru.nathalie.service.weather;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.nathalie.config.AppProperties;
import ru.nathalie.model.game.WeatherCode;

@Component
public class WeatherService {

    private final RestTemplate restTemplate;
    private final AppProperties appProperties;

    public WeatherService(RestTemplate restTemplate, AppProperties appProperties) {
        this.restTemplate = restTemplate;
        this.appProperties = appProperties;
    }

    public WeatherCode weatherCode(Integer gameId) {
        ResponseEntity<WeatherCode> rateResponse =
                restTemplate.exchange(appProperties.getWeatherUrl(), HttpMethod.GET, null,
                        new ParameterizedTypeReference<WeatherCode>() {
                        }, gameId);
        WeatherCode weatherCode = rateResponse.getBody();
        weatherCode.setConditions();

        return weatherCode;
    }
}
