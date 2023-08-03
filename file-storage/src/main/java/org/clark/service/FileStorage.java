package org.clark.service;

import org.clark.entities.FileData;
import org.clark.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileStorage {
    @Autowired
    private FileRepository fileRepository;
    @Value("${files.storage}")
    private String folderLocation;


    public String uploadFile(MultipartFile file) throws IOException {


        String path = folderLocation + "files/" + file.getOriginalFilename();
        FileData fileData = new FileData();
        fileData.setFileName(file.getOriginalFilename());
        fileData.setFilePath(path);
        fileData.setFileType(file.getContentType());
        Files.copy(file.getInputStream(), Paths.get(path),
                StandardCopyOption.REPLACE_EXISTING);
        fileRepository.save(fileData);
        return "file uploaded successfully " + path;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        ArrayList<String> extensions = new ArrayList<>();
        extensions.add("png");
        extensions.add("jpeg");
        extensions.add("jpg");
        String path = null;
        if (extensions.contains(fileExtension)) {
            path = folderLocation + "images/" + file.getOriginalFilename();
            FileData fileData = new FileData();
            fileData.setFileName(file.getOriginalFilename());
            fileData.setFilePath(path);
            fileData.setFileType(file.getContentType());
            Files.copy(file.getInputStream(), Paths.get(path),
                    StandardCopyOption.REPLACE_EXISTING);
            fileRepository.save(fileData);
        }
        return "file uploaded successfully to path: " + path;
    }

    public byte[] downloadImage(String fileName) {
        FileData fileData = fileRepository.findByFileName(fileName);
        String filePath = fileData.getFilePath();

        byte[] image = new byte[0];
        try {
            image = Files.readAllBytes(new File(filePath).toPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
    public List<byte[]> downloadMultipleImages() {
        List<FileData> fileData = fileRepository.findAll();
        System.out.println(fileData);
        List<byte[]> image = new ArrayList<>();
        for(FileData fd : fileData) {
            String filePath = fd.getFilePath();
            try {
                byte[] images = Files.readAllBytes(new File(filePath).toPath());
                image.add(images);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return image;
    }
}
