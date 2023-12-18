package top.xuinrz.kpl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.xuinrz.kpl.payload.Result;
import top.xuinrz.kpl.service.BattleService;
import top.xuinrz.kpl.service.InfoService;
import top.xuinrz.kpl.service.MatchService;
import top.xuinrz.kpl.service.ScheduleService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
public class GlobalController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private BattleService battleService;
    @Autowired
    private InfoService infoService;

    @GetMapping("/schedule")
    Result showSchedule() {
        return Result.success(scheduleService.showSchedule());
    }

    @GetMapping("/match")
    Result getMatch(String match_id) {
        return Result.success(matchService.getMatch(match_id));
    }

    @GetMapping("/battle")
    Result getBattle(String battle_id) {
        return Result.success(battleService.getBattle(battle_id));
    }

    @GetMapping("/team")
    Result getTeams(){
        return Result.success(infoService.getTeams());
    }

    @GetMapping("/player")
    Result getPlayers(){
        return Result.success(infoService.getPlayers());
    }

    @GetMapping("/hero")
    Result getHeroes(){
        return Result.success(infoService.getHeroes());
    }


}
