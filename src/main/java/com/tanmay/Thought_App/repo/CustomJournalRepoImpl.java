package com.tanmay.Thought_App.repo;

import com.tanmay.Thought_App.entity.JournalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomJournalRepoImpl implements CustomJournalRepo{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<JournalEntry> getEntriesByCriteria(String title, String content, LocalDate date) {

        List<Criteria> criteriaList = new ArrayList<>();

        if(title != null && !title.isEmpty()){
            criteriaList.add(Criteria.where("title").regex(title, "i"));
        }

        if(content != null && !content.isEmpty()) {
            criteriaList.add(Criteria.where("content").regex(content, "i"));
        }

        if(date != null) {
            criteriaList.add(Criteria.where("date").is(date));
        }

        Criteria finalCriteria = new Criteria();

        if(!criteriaList.isEmpty()){
            finalCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
        }

        Query query = new Query(finalCriteria);
        return mongoTemplate.find(query, JournalEntry.class);
    }
}
