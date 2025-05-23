package com.tanmay.Thought_App.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JournalEntryResponseDTO {

    private String id;
    private String title;
    private String content;
    private LocalDate date;
}
