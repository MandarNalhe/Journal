package com.mandar.Journal.repository;

import com.mandar.Journal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserImplRepository {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    User user;
    public List<User> getUserWithSA(){
        Query query = new Query();
        query.addCriteria(Criteria.where("mail").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").exists(true));
        return mongoTemplate.find(query,User.class);
    }
}
