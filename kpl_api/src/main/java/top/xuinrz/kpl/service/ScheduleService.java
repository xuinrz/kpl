package top.xuinrz.kpl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import top.xuinrz.kpl.entity.Schedule;
import top.xuinrz.kpl.repository.ScheduleRepository;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    ScheduleRepository scheduleRepository;

    public List<Schedule> showSchedule() {
        return scheduleRepository.findAll();
    }
}
