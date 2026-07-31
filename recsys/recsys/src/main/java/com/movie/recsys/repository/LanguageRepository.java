package com.movie.recsys.repository;


import com.movie.recsys.dto.language.LanguageResponse;
import com.movie.recsys.model.Language;

import java.util.List;

public interface LanguageRepository {

    List<Language> getAllLanguages();

}