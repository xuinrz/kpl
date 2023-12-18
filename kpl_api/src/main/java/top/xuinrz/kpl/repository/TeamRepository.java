package top.xuinrz.kpl.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.xuinrz.kpl.entity.Team;

@Repository
public interface TeamRepository extends MongoRepository<Team,String> {

}
