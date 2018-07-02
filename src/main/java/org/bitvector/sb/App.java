package org.bitvector.sb;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();
        MapConfig mapConfig = new MapConfig();

        mapConfig.setName("posts")
                .setTimeToLiveSeconds(30)
                .setEvictionPolicy(EvictionPolicy.LRU);

        config.setProperty("hazelcast.jmx", "true")
                .getMapConfigs().put("posts", mapConfig);

        return Hazelcast.newHazelcastInstance(config);
    }

}
