package ru.nathalie.model;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Component
@XmlRootElement(name = "report")
public class WeatherCode {

    @XmlElement
    private String code;

    private String condition;

    public WeatherCode() {
    }

    public String getCode() {
        return code;
    }

    public void setConditions() {
        switch (code) {
            case "NMR":
                condition = "Normal weather";
                break;
            case "SRO":
                condition = "Storm";
                break;
            case "HVA":
                condition = "Heave rain";
                break;
            case "T E":
                condition = "Long day";
                break;
            case "FUNDEFINEDG":
                condition = "Fog";
                break;
            default:
                condition = "Wrong weather!";
                break;
        }
    }

    public String getCondition() {
        return condition;
    }
}
