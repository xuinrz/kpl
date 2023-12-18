package top.xuinrz.kpl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import top.xuinrz.kpl.entity.Match;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Match> getMatch(String match_id) {
        Query query = new Query(Criteria.where("match_id").is(match_id));
        return mongoTemplate.find(query, Match.class);
    }

}
