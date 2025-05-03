package com.tanmay.Thought_App.repo;

import com.tanmay.Thought_App.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface JournalRepository extends MongoRepository<JournalEntry, String> {

    @Query("{ '$or': [ { 'title': { $regex: ?0, $options: 'i' } }, { 'content': { $regex: ?0, $options: 'i' } } ] }")
    List<JournalEntry> searchByKeyword(String keyword);
}
