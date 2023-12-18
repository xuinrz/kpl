package top.xuinrz.kpl.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.xuinrz.kpl.entity.Schedule;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule,String> {

}
