package com.tanmay.Thought_App.service;

import com.tanmay.Thought_App.entity.JournalEntry;
import com.tanmay.Thought_App.repo.JournalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService{

    private final JournalRepository journalRepository;

    public JournalServiceImpl(JournalRepository journalRepo){
        this.journalRepository = journalRepo;
    }

    @Override
    public List<JournalEntry> getAllEntries() {
        return journalRepository.findAll();
    }

    @Override
    public JournalEntry saveEntry(JournalEntry entry) {
        return journalRepository.save(entry);
    }

    @Override
    public Optional<JournalEntry> getEntryById(String id) {
        return journalRepository.findById(id);
    }

    @Override
    public void deleteJournalById(String id) {
        journalRepository.deleteById(id);
    }
}
