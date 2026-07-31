package com.movie.recsys.controller;

import com.movie.recsys.model.Language;
import com.movie.recsys.service.LanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    public ResponseEntity<List<Language>> getAllLanguages() {

        return ResponseEntity.ok(
                languageService.getAllLanguages());

    }

}
