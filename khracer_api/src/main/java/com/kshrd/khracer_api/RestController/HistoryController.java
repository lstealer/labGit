package com.kshrd.khracer_api.RestController;

import com.kshrd.khracer_api.Repository.Model.BaseApiResponse;
import com.kshrd.khracer_api.Repository.Model.UserHistoryRS;
import com.kshrd.khracer_api.RestController.Message.InvalidRequestMessage;
import com.kshrd.khracer_api.RestController.Message.SuccessMessage;
import com.kshrd.khracer_api.RestController.RequestModel.HistoryRQ;
import com.kshrd.khracer_api.Service.HistoryService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/kh-racer/v1")
public class HistoryController {
    private HistoryService historyService;
    @Autowired
    public void setHistoryService(HistoryService historyService){
        this.historyService=historyService;
    }
    @PostMapping("/history")
    public ResponseEntity<BaseApiResponse> postHistory(@RequestBody HistoryRQ historyRQ){
        return historyService.postHistoryService(historyRQ);
    }
    @GetMapping("/histories/{id}")
    public ResponseEntity<BaseApiResponse<List<UserHistoryRS>>> getHistories(
            @PathVariable int id,
            @RequestParam(value = "limit",defaultValue = "5") int limit,
            @RequestParam(value = "page",defaultValue="1") int page
           ){
        BaseApiResponse<List<UserHistoryRS>> response=new BaseApiResponse<>();
        List<UserHistoryRS> userHistoryRSList;
        userHistoryRSList=historyService.getUserHistories(id,limit,(page-1)*limit);
        if(userHistoryRSList==null){
            response.setResponse(InvalidRequestMessage.PAGE_NOT_AVAILABLE.value(),
                    true,HttpStatus.NO_CONTENT,
                    null);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        response.setResponse(SuccessMessage.GET_HISTORY_SUCCESS.value(),
                true,
                HttpStatus.OK,
                userHistoryRSList
                );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
