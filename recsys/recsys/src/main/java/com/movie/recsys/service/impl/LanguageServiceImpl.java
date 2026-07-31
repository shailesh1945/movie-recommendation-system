package com.movie.recsys.service.impl;

import com.movie.recsys.dto.language.LanguageResponse;
import com.movie.recsys.model.Language;
import com.movie.recsys.repository.LanguageRepository;
import com.movie.recsys.service.LanguageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public List<Language> getAllLanguages() {

        List<Language> languages = languageRepository.getAllLanguages();

        return languages;
    }

}
