package ru.nathalie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.nathalie.model.game.BattleInfo;
import ru.nathalie.model.game.Dragon;
import ru.nathalie.model.game.GameId;
import ru.nathalie.model.game.WeatherCode;
import ru.nathalie.model.solution.Result;
import ru.nathalie.service.battle.BattleService;
import ru.nathalie.service.weather.WeatherService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class WebController {

    private final BattleService battleService;
    private final WeatherService weatherService;
    private final BattleService solveBattle;
    private Map<Integer, BattleInfo> battleInfoMap = new HashMap<>();

    private final Logger log = LoggerFactory.getLogger(BattleService.class.getName());

    public WebController(BattleService startBattle, WeatherService weatherService,
                         BattleService solveBattle) {
        this.battleService = startBattle;
        this.weatherService = weatherService;
        this.solveBattle = solveBattle;
    }

    @GetMapping("/dragon")
    public String dragonForm(Model model) {
        BattleInfo battleInfo = battleService.battleInfo();

        WeatherCode weather = weatherService.weatherCode(battleInfo.getId());
        log.info(weather.getCode());
        battleInfo.setWeatherCode(weather);

        log.info(String.format("Knight: name:%s, attack %d, armor %d, agility %d, endurance %d",
                battleInfo.getKnight().getName(),
                battleInfo.getKnight().getAttack(),
                battleInfo.getKnight().getArmor(),
                battleInfo.getKnight().getAgility(),
                battleInfo.getKnight().getEndurance()));

        battleInfoMap.put(battleInfo.getId(), battleInfo);

        Dragon dragon = battleInfo.calculateDragon();
        if (dragon != null) {
            model.addAttribute("dragonSuggestion", dragon);
            model.addAttribute("suggestion", "");
        } else {
            model.addAttribute("dragonSuggestion", new Dragon(0, 0, 0, 0));
            model.addAttribute("suggestion", "We won't send your dragon because it's storm!!!");
        }
        model.addAttribute("dragon", new Dragon());
        model.addAttribute("knight", battleInfo.getKnight());
        model.addAttribute("weather", weather);
        model.addAttribute("gameid", battleInfo.getGameId());

        return "dragon";
    }

    @PostMapping("/dragon")
    public String dragonSubmit(@ModelAttribute Dragon dragon,
                               @ModelAttribute Result solution,
                               @ModelAttribute GameId gameid,
                               Model model) {
        BattleInfo battleInfo = battleInfoMap.get(gameid.getGameid());
        battleInfo.setDragon(dragon);
        log.info(String.format("Dragon: ClawSharpness %d, FireBreath %d, ScaleThickness %d, WingStrength %d",
                dragon.getClawSharpness(),
                dragon.getFireBreath(),
                dragon.getScaleThickness(),
                dragon.getWingStrength()));

        model.addAttribute("knight", battleInfo.getKnight());
        model.addAttribute("dragon", dragon);
        model.addAttribute("solution", solution);

        if (battleInfo.getWeatherCode().getCode().equals("SRO")) {
            solution.setResult(solveBattle.solveBattle(battleInfo.getId(), null));
        } else {
            solution.setResult(solveBattle.solveBattle(battleInfo.getId(), dragon));
        }

        log.info(solution.getResult());

        return "result";
    }
}
