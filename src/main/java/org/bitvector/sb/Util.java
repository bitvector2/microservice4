package org.bitvector.sb;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Util {

    @Bean
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();
        MapConfig mapConfig = new MapConfig();

        mapConfig.setName("posts")
                .setTimeToLiveSeconds(30)
                .setEvictionPolicy(EvictionPolicy.LRU);

        config.setInstanceName("org.bitvector.sb")
                .setProperty("hazelcast.jmx", "true")
                .getMapConfigs().put("default", mapConfig);

        return Hazelcast.newHazelcastInstance(config);
    }

}
