package ru.nathalie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.nathalie.model.BattleInfo;
import ru.nathalie.model.Dragon;
import ru.nathalie.model.Result;
import ru.nathalie.model.WeatherCode;
import ru.nathalie.service.battle.BattleService;
import ru.nathalie.service.weather.WeatherService;

@Controller
public class WebController {

    private final BattleService battleService;
    private final WeatherService weatherService;
    private final BattleService solveBattle;
    private final Dragon dragon;
    private BattleInfo battleInfo;
    private WeatherCode weather;

    private final Logger log = LoggerFactory.getLogger(BattleService.class.getName());

    public WebController(BattleService startBattle, WeatherService weatherService,
                         BattleService solveBattle, Dragon dragon, WeatherCode weather) {
        this.battleService = startBattle;
        this.weatherService = weatherService;
        this.solveBattle = solveBattle;
        this.dragon = dragon;
        this.weather = weather;
    }

    @GetMapping()
    public String run() {
        BattleInfo battleInfo = battleService.battleInfo();

        log.info(String.format("attack %d, armor %d, agility %d, endurance %d",
                battleInfo.getKnight().getAttack(),
                battleInfo.getKnight().getArmor(),
                battleInfo.getKnight().getAgility(),
                battleInfo.getKnight().getEndurance()));

        weather = weatherService.weatherCode(battleInfo.getGameId());
        log.info(weather.getCode());

        String solve = solveBattle.solveBattle(battleInfo.getGameId(), this.dragon);
        log.info(solve);

        return solve;
    }

    @GetMapping("/dragon")
    public String dragonForm(Model model) {
        battleInfo = battleService.battleInfo();
        log.info(String.format("Knight: name:%s attack %d, armor %d, agility %d, endurance %d",
                battleInfo.getKnight().getName(),
                battleInfo.getKnight().getAttack(),
                battleInfo.getKnight().getArmor(),
                battleInfo.getKnight().getAgility(),
                battleInfo.getKnight().getEndurance()));

        weather = weatherService.weatherCode(battleInfo.getGameId());
        log.info(weather.getCode());

        model.addAttribute("dragon", new Dragon());
        model.addAttribute("knight", battleInfo.getKnight());
        model.addAttribute("weather", weather);

        return "dragon";
    }


    @PostMapping("/dragon")
    public String dragonSubmit(@ModelAttribute Dragon dragon,
                               @ModelAttribute Result solution,
                               Model model) {

        this.dragon.setClawSharpness(dragon.getClawSharpness());
        this.dragon.setFireBreath(dragon.getFireBreath());
        this.dragon.setScaleThickness(dragon.getScaleThickness());
        this.dragon.setWingStrength(dragon.getWingStrength());
        log.info(String.format("Dragon: ClawSharpness %d, FireBreath %d, ScaleThickness %d, WingStrength %d",
                dragon.getClawSharpness(),
                dragon.getFireBreath(),
                dragon.getScaleThickness(),
                dragon.getWingStrength()));

        model.addAttribute("knight", battleInfo.getKnight());
        model.addAttribute("dragon", this.dragon);
        model.addAttribute("solution", solution);

        solution.setResult(solveBattle.solveBattle(battleInfo.getGameId(), this.dragon));
        log.info(solution.getResult());

        return "result";
    }
}
