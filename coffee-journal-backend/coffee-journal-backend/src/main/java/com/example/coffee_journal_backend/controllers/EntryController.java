package com.example.coffee_journal_backend.controllers;

import com.example.coffee_journal_backend.dto.EntryDTO;
import com.example.coffee_journal_backend.models.CoffeeShop;
import com.example.coffee_journal_backend.models.Entry;
import com.example.coffee_journal_backend.repositories.EntryRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.awt.*;

@RestController
@RequestMapping("/entries")
public class EntryController {

    @Autowired
    EntryRepository entryRepository;

    @GetMapping(value="", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllEntries() {
        List allEntries = (List) entryRepository.findAll();
        return new ResponseEntity<>(allEntries, HttpStatus.OK);
    }

    @GetMapping(value="/details/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEntryById(@PathVariable int id) throws NoResourceFoundException {
        Entry entry = entryRepository.findById(id).orElse(null);
        if (entry == null) {
            String path = "/entries/details/" + id;
            throw new NoResourceFoundException(HttpMethod.GET, path);
        } else {
            return new ResponseEntity<>(entry, HttpStatus.OK);
        }
    }

    @PostMapping(value="/add", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewEntry(@Valid @RequestBody EntryDTO entryData) {
        Entry entry = new Entry(entryData.getDrinkOrder(), entryData.getRating(), entryData.getReview(), entryData.isWouldRecommend(), entryData.getVisitDate());
        entryRepository.save(entry);
        return new ResponseEntity<>(entry, HttpStatus.CREATED);
    }

}
