package top.xuinrz.kpl.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ClusterConnectionMode;
import com.mongodb.connection.ClusterType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;

@Configuration
public class MongoDBConfig {
    @Bean
    public MongoClient rawClient() {
        MongoCredential credential = MongoCredential.createCredential("eyhawgehza", "dgsafawfaw", "asffaSfvA".toCharArray());
        MongoClientSettings setting = MongoClientSettings.builder()
                .credential(credential)
                .applyToClusterSettings(builder ->
                        builder.hosts(Arrays.asList(new ServerAddress("x.xxx.xx.xx", Integer.parseInt("27017"))))
                                .mode(ClusterConnectionMode.SINGLE)
                                .requiredClusterType(ClusterType.STANDALONE)
                ).build();
        return MongoClients.create(setting);
    }


    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(rawClient(), "kpl");
    }
}
