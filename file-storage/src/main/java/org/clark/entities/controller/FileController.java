package org.clark.entities.controller;

import org.clark.service.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class FileController {
    @Autowired
    private FileStorage storage;
    @PostMapping("/upload-file")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return storage.uploadFile(file);

    }
    @PostMapping("/upload-image")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return storage.uploadImage(file);
    }
    @GetMapping(value = "/download-image/{fileName}",produces =  MediaType.IMAGE_PNG_VALUE)
    public byte[] downloadImage(@PathVariable("fileName") String fileName) {
        return storage.downloadImage(fileName);
    }

    @GetMapping(value = "/download-all-images" )
    public ResponseEntity<Resource> downloadAllImages() {
        List<byte[]> images = storage.downloadMultipleImages();

        byte[] mergedImages = mergeImages(images);
        ByteArrayResource resource = new ByteArrayResource(mergedImages);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    private byte[] mergeImages(List<byte[]> images) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (byte[] image : images) {
            try {
                baos.write(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return baos.toByteArray();
    }


}
