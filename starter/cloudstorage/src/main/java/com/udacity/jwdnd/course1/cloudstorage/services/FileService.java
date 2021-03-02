package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.configs.StorageProperties;
import com.udacity.jwdnd.course1.cloudstorage.exceptions.StorageException;
import com.udacity.jwdnd.course1.cloudstorage.exceptions.StorageFileNotFoundException;
import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class FileService {
    private final FileMapper fileMapper;
    private final Path rootLocation;

    public FileService(FileMapper fileMapper, StorageProperties properties) {
        this.fileMapper = fileMapper;
        this.rootLocation = Paths.get(properties.getLocation());
    }

    public void init() {
        try {
            Files.createDirectories(this.rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not create file upload directory", e);
        }
    }

    public List<File> getAllFiles() {
        return this.fileMapper.getAll();
    }

    public File getById(int id) {
        List<File> files = this.fileMapper.getById(id);
        if (files.size() == 0) {
            throw new StorageFileNotFoundException("Could not find file with id: " + id);
        }
        return files.get(0);
    }

    public int create(MultipartFile uploadedFile, int userId) {
        if (uploadedFile.isEmpty()) {
            throw new StorageException("Cannot store an empty file.");
        }
        Path destinationFilePath = this.saveFileLocalCopy(uploadedFile);
//        String blobData = this.getBlobDataFromFile(uploadedFile);
        File file = new File(
                null,
                destinationFilePath.getFileName().toString(),
                uploadedFile.getContentType(),
                Long.toString(uploadedFile.getSize()),
                userId,
                ""
        );
        return this.fileMapper.insert(file);
    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = this.rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
            return resource;
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename);
        }
    }

    public void delete(int id) {
        File file = this.getById(id);
        String filename = file.getName();
        try {
            Files.delete(this.rootLocation.resolve(filename));
        } catch (IOException e) {
            throw new StorageException("Cannot delete file: " + filename);
        }
        this.fileMapper.delete(id);
    }

    private Path saveFileLocalCopy(MultipartFile uploadedFile) {
        String originalFilename = Objects.requireNonNull(uploadedFile.getOriginalFilename());
        Path destinationFile = this.rootLocation.resolve(Paths.get(originalFilename)).normalize().toAbsolutePath();
        if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
            throw new StorageException("Cannot store any file outside current directory.");
        }
        try(InputStream inputStream = uploadedFile.getInputStream()) {
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            return destinationFile;
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    private String getBlobDataFromFile(MultipartFile uploadedFile) {
        byte[] bytes;
        try {
            bytes = uploadedFile.getInputStream().readAllBytes();
        } catch (IOException e) {
            throw new StorageException("Failed to read file data", e);
        }
        return Arrays.toString(bytes);
    }
}
