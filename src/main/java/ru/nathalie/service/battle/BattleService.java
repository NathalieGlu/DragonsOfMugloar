package ru.nathalie.service.battle;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.nathalie.config.AppProperties;
import ru.nathalie.model.BattleInfo;
import ru.nathalie.model.Dragon;
import ru.nathalie.model.Solution;

@Component
public class BattleService {
    private final RestTemplate restTemplate;
    private final AppProperties appProperties;

    public BattleService(RestTemplate restTemplate, AppProperties appProperties) {
        this.restTemplate = restTemplate;
        this.appProperties = appProperties;
    }

    public BattleInfo battleInfo() {

        ResponseEntity<BattleInfo> rateResponse =
                restTemplate.exchange(appProperties.getGameurl(), HttpMethod.GET, null,
                        new ParameterizedTypeReference<BattleInfo>() {
                        });

        return rateResponse.getBody();
    }

    public String solveBattle(Integer gameId, Dragon dragon) {
        PropertyConfigurator.configure("log4j.properties");

        Solution solution = new Solution(dragon);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Solution> request = new HttpEntity<>(solution, headers);

        ResponseEntity<String> rateResponse =
                restTemplate.exchange(appProperties.getSolutionUrl(), HttpMethod.PUT, request,
                        new ParameterizedTypeReference<String>() {
                        }, gameId);

        return rateResponse.getBody();
    }
}
