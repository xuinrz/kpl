package top.xuinrz.kpl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import top.xuinrz.kpl.entity.Battle;

import java.util.List;

@Service
public class BattleService {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Battle> getBattle(String battle_id) {
        Query query = new Query(Criteria.where("battle_id").is(battle_id));
        return mongoTemplate.find(query, Battle.class);
    }

}
