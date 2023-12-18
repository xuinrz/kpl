package top.xuinrz.kpl.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "team")
public class Team {
    @Id
    private String _id;
    private Object team_info;
    private Object statistics_info;

}
