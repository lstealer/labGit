package com.kshrd.khracer_api.RestController;

import com.kshrd.khracer_api.Repository.Model.BaseApiResponse;
import com.kshrd.khracer_api.Repository.Model.ImageDto;
import com.kshrd.khracer_api.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/kh-racer/v1/")
public class FileController {

    @Value("${file.base.url}")
    private String imageUrl;
    private ImageService service;

    @Autowired
    public void setService(ImageService service){
        this.service=service;
    }

    @PostMapping("/uploads")
    public ResponseEntity<BaseApiResponse<String>> upLoadFile(@RequestParam("file") MultipartFile[] file) {
        BaseApiResponse response = new BaseApiResponse();
        List<String> path=new ArrayList<>();
        for(int i=0;i<file.length;i++) {
            File uploadedFile =
                    new File("src/main/resources/imageUpload/" , UUID.randomUUID() + "." + file[i].getOriginalFilename().substring(file[i].getOriginalFilename().lastIndexOf(".") + 1));
            try {
                uploadedFile.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(uploadedFile);
                fileOutputStream.write(file[i].getBytes());
                fileOutputStream.close();
                path.add(imageUrl + uploadedFile.getName());
                service.postImage(uploadedFile.getName());
            }    catch (IOException e) {
                e.printStackTrace();
            }
        }
        response.setMessage("You have uploaded File successfully");
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        response.setData(path);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/file-{id}")
    public ResponseEntity<BaseApiResponse<ImageDto>> getImage(@PathVariable int id){
        BaseApiResponse response = new BaseApiResponse();
         ImageDto image= service.getImageById(id);
         image.setImgpath(imageUrl+image.getImgpath());
        response.setMessage("You have get File successfully");
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        response.setData(image);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/files")
    public ResponseEntity<BaseApiResponse<List<ImageDto>>> getImages(){
        BaseApiResponse response = new BaseApiResponse();
        List<ImageDto> images= service.getImages();
        for(ImageDto i:images)
        i.setImgpath(imageUrl+i.getImgpath());
        response.setMessage("You have get Files successfully");
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        response.setData(images);
        return ResponseEntity.ok(response);
    }
}

