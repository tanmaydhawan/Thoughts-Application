package com.tanmay.Thought_App.repo;

import com.tanmay.Thought_App.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalEntry, String> {
}
