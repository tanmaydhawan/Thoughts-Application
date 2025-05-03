package com.tanmay.Thought_App.controller;

import com.tanmay.Thought_App.dto.JournalEntryRequestDTO;
import com.tanmay.Thought_App.dto.JournalEntryResponseDTO;
import com.tanmay.Thought_App.entity.JournalEntry;
import com.tanmay.Thought_App.service.JournalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/thoughts")
public class JournalController {

    private final JournalService journalService;

    public JournalController (JournalService journalService){
        this.journalService = journalService;
    }

    @GetMapping
    public ResponseEntity<List<JournalEntryResponseDTO>> getAllEntries(){
        return new ResponseEntity<>(journalService.getAllEntries(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntryResponseDTO> saveNewEntry(@RequestBody JournalEntryRequestDTO journalEntryRequestDTO){
        return new ResponseEntity<>(journalService.saveEntry(journalEntryRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{journalId}")
    public ResponseEntity<JournalEntryResponseDTO> getEntry (@PathVariable String journalId){
        return new ResponseEntity<>(journalService.getEntryById(journalId), HttpStatus.FOUND);
    }

    @DeleteMapping("/{journalId}")
    public ResponseEntity<?> deleteEntry (@PathVariable String journalId){
        if(journalService.deleteJournalById(journalId)) {
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{journalId}")
    public ResponseEntity<JournalEntryResponseDTO> editAnEntry (@PathVariable String journalId,
                                                     @RequestBody JournalEntryRequestDTO journalEntryRequestDTO){
        return new ResponseEntity<>(journalService.editEntry(journalId, journalEntryRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<JournalEntryResponseDTO>> searchEntries
            (@RequestParam(required = false) String keyword){
        return new ResponseEntity<>(journalService.searchEntriesByFilters(keyword), HttpStatus.FOUND);
    }

}
