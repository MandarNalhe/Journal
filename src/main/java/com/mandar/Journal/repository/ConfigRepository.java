package com.mandar.Journal.repository;

import com.mandar.Journal.entity.Config;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigRepository extends MongoRepository<Config, ObjectId> {

    Config findByKey(String api);
}
