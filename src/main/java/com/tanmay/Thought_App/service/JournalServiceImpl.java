package com.tanmay.Thought_App.service;

import com.tanmay.Thought_App.entity.JournalEntry;
import com.tanmay.Thought_App.repo.JournalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        if(entry.getDate()== null){
            entry.setDate(LocalDate.now());
        }
        return journalRepository.save(entry);
    }

    @Override
    public Optional<JournalEntry> getEntryById(String journalId) {
        return journalRepository.findById(journalId);
    }

    @Override
    public void deleteJournalById(String journalId) {
        journalRepository.deleteById(journalId);
    }

    @Override
    public JournalEntry editEntry(String journalId, JournalEntry entry) {

        Optional<JournalEntry> existingEntry = journalRepository.findById(journalId);

        JournalEntry updatedJournal = new JournalEntry();

        if(existingEntry.isPresent()){
            updatedJournal.setTitle(entry.getTitle());
            updatedJournal.setContent(entry.getContent());
            updatedJournal.setDate(LocalDate.now());
        }
        return journalRepository.save(updatedJournal);
    }
}
