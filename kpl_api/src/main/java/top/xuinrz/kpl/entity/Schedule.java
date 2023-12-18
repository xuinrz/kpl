package top.xuinrz.kpl.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "schedule")
public class Schedule {
    @Id
    private String _id;
    private String match_id;
//    private String match_address;
    private String start_time;
    private Object camp1;
    private Object camp2;
}
