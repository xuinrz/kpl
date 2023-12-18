package top.xuinrz.kpl.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.xuinrz.kpl.entity.Player;

@Repository
public interface PlayerRepository extends MongoRepository<Player,String> {

}
