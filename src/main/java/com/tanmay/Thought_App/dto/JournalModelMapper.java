package com.tanmay.Thought_App.dto;

import com.tanmay.Thought_App.entity.JournalEntry;
import org.springframework.stereotype.Component;

@Component
public class JournalModelMapper {

    // Journal Entry to DTO
    public JournalEntryResponseDTO toDto(JournalEntry entry){

        JournalEntryResponseDTO journalEntryResponseDTO = new JournalEntryResponseDTO();
        journalEntryResponseDTO.setId(entry.getId());
        journalEntryResponseDTO.setTitle(entry.getTitle());
        journalEntryResponseDTO.setContent(entry.getContent());
        journalEntryResponseDTO.setDate(entry.getDate());

        return  journalEntryResponseDTO;
    }

//    private toEntity

}
