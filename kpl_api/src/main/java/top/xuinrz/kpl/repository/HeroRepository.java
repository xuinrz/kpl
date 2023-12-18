package top.xuinrz.kpl.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.xuinrz.kpl.entity.Hero;

@Repository
public interface HeroRepository extends MongoRepository<Hero,String> {

}
