package com.tanmay.Thought_App.repo;

import com.tanmay.Thought_App.entity.JournalEntry;
import java.time.LocalDate;
import java.util.List;

public interface CustomJournalRepo {

    public List<JournalEntry> getEntriesByCriteria(String title, String content, LocalDate date);
}
