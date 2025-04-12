package com.tanmay.Thought_App.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "thoughts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JournalEntry {

    @Id
    private String id;
    private String title;
    private String content;
    private LocalDate date;

}
