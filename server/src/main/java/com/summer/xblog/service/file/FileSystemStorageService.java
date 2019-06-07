package com.summer.xblog.service.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService {
    @Autowired
    private StorageProperties properties;

    @Secured({"ROLE_ADMIN"})
    public void store(Principal principal, MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file " + filename);
        }
        try (InputStream inputStream = file.getInputStream()) {
            Path path = this.properties.getPath(principal.getName());
            if(!path.toFile().exists()) {
                this.init(path);
            }
            Files.copy(inputStream, path.resolve(filename),
                StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    public Stream<Path> loadAll(Principal principal) {
        try {
            Path path = this.properties.getPath(principal.getName());
            return Files.walk(path, 1)
                .filter(filePath -> !filePath.equals(path))
                .map(path::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    public Path load(String author, String filename) {
        Path path = this.properties.getPath(author);
        return path.resolve(filename);
    }

    public Resource loadAsResource(String author, String filename) {
        try {
            Path file = load(author, filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Secured({"ROLE_ADMIN"})
    public void deleteAll(String username) throws IOException {
        Path path = this.properties.getPath(username);
        FileSystemUtils.deleteRecursively(path);
    }

    @Secured("ROLE_ADMIN")
    public void delete(String author, String filename) throws IOException{
        Path load = this.load(author, filename);
        FileSystemUtils.deleteRecursively(load);
    }

    public void init(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
