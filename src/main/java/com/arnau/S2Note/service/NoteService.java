package com.arnau.S2Note.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteService {

    private final Map<String, List<String>> notes = new HashMap<>();

    public NoteService() {
        notes.put("alice@mymail.com", List.of("Note1", "Note2"));
        notes.put("bob@mymail.com", List.of("Note3", "Note4", "Note5"));
    }

    public List<String> getNotesForUser(String email) {
        return notes.getOrDefault(email, List.of());
    }
}
