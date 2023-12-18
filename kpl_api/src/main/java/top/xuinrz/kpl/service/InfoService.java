package top.xuinrz.kpl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import top.xuinrz.kpl.entity.Hero;
import top.xuinrz.kpl.entity.Player;
import top.xuinrz.kpl.entity.Team;
import top.xuinrz.kpl.repository.HeroRepository;
import top.xuinrz.kpl.repository.PlayerRepository;
import top.xuinrz.kpl.repository.TeamRepository;

import java.util.List;

@Service
public class InfoService {

    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    HeroRepository heroRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    PlayerRepository playerRepository;


    public List<Hero> getHeroes() {
        return heroRepository.findAll();
    }

    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

}
