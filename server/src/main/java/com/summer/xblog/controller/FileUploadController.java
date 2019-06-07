package com.summer.xblog.controller;

import com.summer.xblog.dto.CommonDTO;
import com.summer.xblog.service.file.FileSystemStorageService;
import com.summer.xblog.service.file.StorageFileNotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
public class FileUploadController {
    @Autowired
    private FileSystemStorageService storageService;

    @GetMapping("files")
    public List<String> listUploadedFiles(Principal principal) {

        return storageService.loadAll(principal).map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString(), principal.getName()).build().toString())
                .collect(Collectors.toList());
    }

    @ApiOperation(value="文件下载",produces="application/octet-stream")
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename, @RequestParam("author") String author) {

        Resource file = storageService.loadAsResource(author, filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/file")
    public void handleFileUpload(Principal principal, @RequestParam("file") MultipartFile file) {
        storageService.store(principal, file);
    }

    @DeleteMapping("/file")
    public CommonDTO deleteFile(Principal principal, @RequestParam("filename") String filename) throws IOException {
        storageService.delete(principal.getName(), filename);
        return CommonDTO.success(filename);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
