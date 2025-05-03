package com.tanmay.Thought_App.service;

import com.tanmay.Thought_App.dto.JournalEntryRequestDTO;
import com.tanmay.Thought_App.dto.JournalEntryResponseDTO;
import com.tanmay.Thought_App.entity.JournalEntry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface JournalService {

    public List<JournalEntryResponseDTO> getAllEntries();
    public JournalEntryResponseDTO saveEntry (JournalEntryRequestDTO journalEntryRequestDTO);
    public JournalEntryResponseDTO getEntryById (String entryId);
    public boolean deleteJournalById (String entryId);
    public JournalEntryResponseDTO editEntry (String entryId, JournalEntryRequestDTO journalEntryRequestDTO);
    public List<JournalEntryResponseDTO> searchEntriesByFilters(String keyword);
}
