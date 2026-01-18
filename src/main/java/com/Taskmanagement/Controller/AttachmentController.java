package com.Taskmanagement.Controller;

import java.io.InputStream;
import java.net.URI;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Taskmanagement.Service.AttachmentService;
import com.TaskmanagementProject.Entity.Attachment;

import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping("/uploadFile/{issueId}")
    public ResponseEntity<Attachment> uploadFile(
            @PathVariable Long issueId,
            @RequestParam MultipartFile file,
            @RequestParam String uploadBy)
            throws FileUploadException, ValidationException {

        return ResponseEntity.ok(
                attachmentService.uploadFile(issueId, file, uploadBy)
        );
    }

    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<Void> download(@PathVariable Long id) {

        Attachment attachment = attachmentService.getFileById(id);

        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, attachment.getStoragePath())
                .build();
    }

    @GetMapping("/downloadFile/stream/{id}")
    public ResponseEntity<Resource> downloadFileForSystem(@PathVariable Long id) throws Exception {

        Attachment attach = attachmentService.getFileById(id);

        URI uri = URI.create(attach.getStoragePath());
        InputStream inputStream = uri.toURL().openStream();

        InputStreamResource resource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attach.getFileName() + "\""
                )
                .contentType(MediaType.parseMediaType(attach.getContentType()))
                .body(resource);
    }

    @DeleteMapping("/deleteFile/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) throws FileUploadException {

        attachmentService.deleteFile(id);
        return ResponseEntity.ok("Attachment deleted successfully");
    }
}
