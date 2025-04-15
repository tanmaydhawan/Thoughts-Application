package com.tanmay.Thought_App.service;

import com.tanmay.Thought_App.entity.JournalEntry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface JournalService {

    public List<JournalEntry> getAllEntries();
    public JournalEntry saveEntry (JournalEntry entry);
    public Optional<JournalEntry> getEntryById (String entryId);
    public boolean deleteJournalById (String entryId);
    public JournalEntry editEntry (String entryId, JournalEntry entry);
}
