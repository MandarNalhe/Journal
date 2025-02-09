package com.mandar.Journal.repository;

import com.mandar.Journal.entity.Journal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface JournalRepository extends MongoRepository<Journal, ObjectId> {
    Optional<Journal> findByTitle(String title);

    void deleteByTitle(String title);
}
