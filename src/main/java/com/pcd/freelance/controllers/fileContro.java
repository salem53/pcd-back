package com.pcd.freelance.controllers;

import com.pcd.freelance.entities.uploadFile;
import com.pcd.freelance.repositories.FileRepo;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/file")
public class fileContro {
    @Autowired
    FileRepo fileRepo;

    @PostMapping("/uploadImage")
    public ResponseEntity.BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        uploadFile img = new uploadFile(compressBytes(file.getBytes()), file.getOriginalFilename(), file.getContentType());

        fileRepo.save(img);
        return ResponseEntity.status(HttpStatus.OK);
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    @PostMapping("/uploadFile")
    public void processUpload(@RequestParam("imageFile") MultipartFile file) throws IOException {
        FileOutputStream outputStream = null;
        String projectPath = System.getProperty("user.dir") + "/Files/"; //projectPath
        System.out.println(projectPath);
        String filePath = projectPath + file.getOriginalFilename();
        try
        {
            outputStream = new FileOutputStream(new File(filePath));
            outputStream.write(file.getInputStream().read());
            outputStream.close();
            uploadFile img = new uploadFile(file.getOriginalFilename(), file.getContentType(),filePath);

            fileRepo.save(img);
        }
        catch (Exception e) {
            System.out.println("Error while saving file");


        }
    }
}
