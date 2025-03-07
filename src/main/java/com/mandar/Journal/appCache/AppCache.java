package com.mandar.Journal.appCache;

import com.mandar.Journal.entity.Config;
import com.mandar.Journal.repository.ConfigRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class AppCache {
    public HashMap<String,String> cache = new HashMap<>();
    @Autowired
    ConfigRepository configRepo;
    @Scheduled(cron = "10 * * * *")
    @PostConstruct
    void init(){
        List<Config> all= configRepo.findAll();
        for (Config config: all) {
            cache.put(config.getKey(), config.getValue());
        }
    }
}
