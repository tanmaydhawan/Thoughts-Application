package com.tanmay.Thought_App.controller;

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
    public ResponseEntity<List<JournalEntry>> getAllEntries(){
        return new ResponseEntity<>(journalService.getAllEntries(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> saveNewEntry(@RequestBody JournalEntry entry){
        return new ResponseEntity<>(journalService.saveEntry(entry), HttpStatus.CREATED);
    }

    @GetMapping("/{journalId}")
    public ResponseEntity<JournalEntry> getEntry (@PathVariable String journalId){
        return journalService.getEntryById(journalId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{journalId}")
    public ResponseEntity<?> deleteEntry (@PathVariable String journalId){
        journalService.deleteJournalById(journalId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{journalId}")
    public ResponseEntity<JournalEntry> editAnEntry (@PathVariable String journalId,
                                                     @RequestBody JournalEntry entry){
        return new ResponseEntity<>(journalService.editEntry(journalId, entry), HttpStatus.OK);
    }
}
