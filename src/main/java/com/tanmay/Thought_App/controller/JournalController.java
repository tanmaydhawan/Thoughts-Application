package com.tanmay.Thought_App.controller;

import com.tanmay.Thought_App.entity.JournalEntry;
import com.tanmay.Thought_App.service.JournalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/thoughts")
public class JournalController {

    private final JournalService journalService;

    public JournalController (JournalService journalService){
        this.journalService = journalService;
    }

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllJournalEntries(){
        return new ResponseEntity<>(journalService.getAllEntries(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> saveNewJournalEntry(@RequestBody JournalEntry entry){
        if(entry.getDate()== null){
            entry.setDate(LocalDate.now());
        }
        return new ResponseEntity<>(journalService.saveEntry(entry), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalEntry> getEntryById (@PathVariable String id){
        return journalService.getEntryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
