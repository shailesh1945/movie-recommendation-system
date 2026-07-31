package com.movie.recsys.service.impl;

import com.movie.recsys.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Set;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private static final String UPLOAD_DIR =
            "uploads/movies";

    private static final Set<String> ALLOWED_TYPES = Set.of(

            "image/jpeg",

            "image/png",

            "image/webp"

    );

    @Override
    public String savePoster(MultipartFile file) {

        if (file == null || file.isEmpty()) {

            return null;

        }

        if (!ALLOWED_TYPES.contains(file.getContentType())) {

            throw new RuntimeException(
                    "Only JPG, PNG and WEBP images are allowed."
            );

        }

        try {

            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {

                Files.createDirectories(uploadPath);

            }

            String extension = StringUtils.getFilenameExtension(
                    file.getOriginalFilename());

            String fileName = UUID.randomUUID() + "." + extension;

            Path destination =
                    uploadPath.resolve(fileName);

            Files.copy(
                    file.getInputStream(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING);

            return "/uploads/movies/" + fileName;

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to upload image.",
                    e);

        }

    }

}