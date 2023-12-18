package top.xuinrz.kpl.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "match")
public class Match {
    @Id
    private String _id;
    private Object results;

}
