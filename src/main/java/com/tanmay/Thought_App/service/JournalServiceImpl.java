package com.tanmay.Thought_App.service;

import com.tanmay.Thought_App.dto.JournalEntryRequestDTO;
import com.tanmay.Thought_App.dto.JournalEntryResponseDTO;
import com.tanmay.Thought_App.dto.JournalModelMapper;
import com.tanmay.Thought_App.entity.JournalEntry;
import com.tanmay.Thought_App.exception.ResourceNotFoundException;
import com.tanmay.Thought_App.repo.JournalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        return journalEntry.map(journalModelMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find the Thought you are looking for!"));
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
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find the Thought you are looking for!"));

        existingEntry.setTitle(journalEntryRequestDTO.getTitle());
        existingEntry.setContent(journalEntryRequestDTO.getContent());
        existingEntry.setDate(LocalDate.now());

        JournalEntry editedEntry = journalRepository.save(existingEntry);
        return journalModelMapper.toDto(editedEntry);
    }

    @Override
    public List<JournalEntryResponseDTO> searchEntriesByFilters(String keyword) {

        List<JournalEntry> results;

        if (keyword != null){
            results = journalRepository.searchByKeyword(keyword);
        }
        else {
            results = journalRepository.findAll();
        }
        return results.stream().map(journalModelMapper::toDto).toList();
    }

    @Override
    public List<JournalEntryResponseDTO> searchEntriesDynamic(String title, String content, LocalDate date) {

        List<JournalEntry> entriesByCriteria = journalRepository.getEntriesByCriteria(title, content, date);
        return entriesByCriteria.stream()
                .map(journalModelMapper::toDto)
                .toList();
    }
}
