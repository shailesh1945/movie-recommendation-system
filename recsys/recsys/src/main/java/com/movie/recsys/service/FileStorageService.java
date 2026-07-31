package com.movie.recsys.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String savePoster(MultipartFile file);

}