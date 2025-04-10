package com.mandar.Journal.appCache;

import com.mandar.Journal.entity.Config;
import com.mandar.Journal.repository.ConfigRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class AppCache {
    @Autowired
    private RedisTemplate<String, String> redisCache = new RedisTemplate<>();
    public HashMap<String,String> cache = new HashMap<>();
    @Autowired
    ConfigRepository configRepo;
//    @Scheduled(cron = "10 * * * * *")
//    @PostConstruct
//    void init(){
//        List<Config> all= configRepo.findAll();
//        for (Config config: all) {
//            cache.put(config.getKey(), config.getValue());
//        }
//    }

    public String getData(String api) {
        if(! redisCache.hasKey(api)){
            String value = configRepo.findByKey(api).getValue();
            redisCache.opsForValue().set(api,value);
            return value;
        }else {
            return redisCache.opsForValue().get(api);
        }
    }
}
