package ru.nathalie.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class AppProperties {
    private String gameurl;
    private String weatherUrl;
    private String solutionUrl;

    public String getGameurl() {
        return gameurl;
    }

    public void setGameurl(String gameurl) {
        this.gameurl = gameurl;
    }

    public String getWeatherUrl() {
        return weatherUrl;
    }

    public void setWeatherUrl(String weatherUrl) {
        this.weatherUrl = weatherUrl;
    }

    public String getSolutionUrl() {
        return solutionUrl;
    }

    public void setSolutionUrl(String solutionUrl) {
        this.solutionUrl = solutionUrl;
    }
}
