package com.kshrd.khracer_api.RestController;


import com.kshrd.khracer_api.Repository.Model.BaseApiResponse;
import com.kshrd.khracer_api.Repository.Model.KHContentModel;
import com.kshrd.khracer_api.Repository.Model.TypingContentModel;
import com.kshrd.khracer_api.RestController.Message.SuccessMessage;
import com.kshrd.khracer_api.Service.Imp.ContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/kh-racer/v1")
@CrossOrigin(origins = "*")
public class ContentController {
    private ContentServiceImpl contentService;
    @Autowired
    public void setContentService(ContentServiceImpl contentService){
        this.contentService=contentService;
    }

    //get 1 randomly
    //waiting for double check
    @GetMapping("/content")
    public  ResponseEntity<BaseApiResponse> getRandomContent(){
        BaseApiResponse response = new BaseApiResponse();
            response.setMessage(SuccessMessage.GET_CONTENT_SUCCESS.value());
            response.setData(contentService.selectRandomContent());
            response.setStatus(HttpStatus.OK);
            response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    //
    @PostMapping("/content")
    public  ResponseEntity<BaseApiResponse> setContent(@RequestBody TypingContentModel typingContentModel){
        BaseApiResponse response = new BaseApiResponse();
//        System.out.println("hi2"+typingContentModel.getKhContent());
        contentService.InsertContent(typingContentModel);
        if(contentService.InsertContent(typingContentModel)) {
            response.setMessage("You have added Content successfully");
            response.setData(true);
            response.setStatus(HttpStatus.OK);
            response.setTime(new Timestamp(System.currentTimeMillis()));
        }
            return ResponseEntity.ok(response);
    }


    @DeleteMapping("/content/{id}")
    public  ResponseEntity<BaseApiResponse> deleteContent(@PathVariable int id){
        BaseApiResponse response = new BaseApiResponse();
        if(contentService.DeleteContent(id)) {
            response.setMessage("You have deleted Content successfully");
//            response.setData(null);
            response.setStatus(HttpStatus.OK);
            response.setTime(new Timestamp(System.currentTimeMillis()));
        }
        return ResponseEntity.ok(response);

    }
    @PatchMapping("/content")
    public  ResponseEntity<BaseApiResponse> updateKhContent(@RequestBody KHContentModel khContentModel){
        BaseApiResponse response = new BaseApiResponse();
        if(contentService.UpdateKHContent(khContentModel)) {
            response.setMessage("You have Updated Content successfully");
            response.setData(true);
            response.setStatus(HttpStatus.OK);
            response.setTime(new Timestamp(System.currentTimeMillis()));
        }
        else{
            response.setMessage("You have Updated Content unsuccessfully");
            response.setData(false);
            response.setStatus(HttpStatus.OK);
            response.setTime(new Timestamp(System.currentTimeMillis()));
        }
        return ResponseEntity.ok(response);
    }
}
