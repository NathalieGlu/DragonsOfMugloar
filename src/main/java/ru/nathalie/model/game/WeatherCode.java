package ru.nathalie.model.game;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Component
@XmlRootElement(name = "report")
public class WeatherCode {

    @XmlElement
    private String code;
    private String condition;
    private int hashCode = 0;

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
                condition = "Heavy rain";
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BattleInfo))
            return false;
        WeatherCode weatherCode = (WeatherCode) o;
        return weatherCode.code.equals(code) && weatherCode.condition.equals(condition);
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = code.hashCode();
            result = 31 * result + condition.hashCode();
            hashCode = result;
        }
        return result;
    }
}
