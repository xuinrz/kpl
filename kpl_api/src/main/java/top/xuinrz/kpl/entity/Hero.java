package top.xuinrz.kpl.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "hero")
public class Hero {
    @Id
    private String _id;
    private Object hero_info;
    private Object statistics_info;
    private Object bp_statistics_info;

}
