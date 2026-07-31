package com.movie.recsys.service;

import com.movie.recsys.dto.language.LanguageResponse;
import com.movie.recsys.model.Language;

import java.util.List;

public interface LanguageService {

    List<Language> getAllLanguages();

}
