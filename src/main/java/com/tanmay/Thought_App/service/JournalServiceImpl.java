package com.tanmay.Thought_App.service;

import com.tanmay.Thought_App.dto.JournalEntryRequestDTO;
import com.tanmay.Thought_App.dto.JournalEntryResponseDTO;
import com.tanmay.Thought_App.dto.JournalModelMapper;
import com.tanmay.Thought_App.entity.JournalEntry;
import com.tanmay.Thought_App.repo.JournalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JournalServiceImpl implements JournalService{

    private final JournalRepository journalRepository;
    private final JournalModelMapper journalModelMapper;


    public JournalServiceImpl(JournalRepository journalRepo, JournalModelMapper journalModelMapper){
        this.journalRepository = journalRepo;
        this.journalModelMapper = journalModelMapper;
    }

    @Override
    public List<JournalEntryResponseDTO> getAllEntries() {

        List<JournalEntry> journalEntries = journalRepository.findAll();
        return journalEntries.stream().map(journalModelMapper::toDto).toList();

    }

    @Override
    public JournalEntryResponseDTO saveEntry(JournalEntryRequestDTO journalEntryRequestDTO) {

        JournalEntry journalEntry = journalModelMapper.toEntity(journalEntryRequestDTO);

        if(journalEntry.getDate()== null){
            journalEntry.setDate(LocalDate.now());
        }
        JournalEntry savedJournalEntry = journalRepository.save(journalEntry);
        return journalModelMapper.toDto(savedJournalEntry);
    }

    @Override
    public JournalEntryResponseDTO getEntryById(String journalId) {

        Optional<JournalEntry> journalEntry = journalRepository.findById(journalId);
        return journalEntry.map(journalModelMapper::toDto).orElse(null);
    }

    @Override
    public boolean deleteJournalById(String journalId) {
        if(journalRepository.existsById(journalId)){
            journalRepository.deleteById(journalId);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public JournalEntryResponseDTO editEntry(String journalId, JournalEntryRequestDTO journalEntryRequestDTO) {

        JournalEntry existingEntry = journalRepository.findById(journalId)
                .orElse(null);

        existingEntry.setTitle(journalEntryRequestDTO.getTitle());
        existingEntry.setContent(journalEntryRequestDTO.getContent());
        existingEntry.setDate(LocalDate.now());

        JournalEntry editedEntry = journalRepository.save(existingEntry);
        return journalModelMapper.toDto(editedEntry);
    }
}
