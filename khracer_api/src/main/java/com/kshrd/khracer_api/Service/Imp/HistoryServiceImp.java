package com.kshrd.khracer_api.Service.Imp;

import com.kshrd.khracer_api.Repository.HistoryRepo;
import com.kshrd.khracer_api.Repository.IContentRepo;
import com.kshrd.khracer_api.Repository.Model.BaseApiResponse;
import com.kshrd.khracer_api.Repository.Model.UserHistoryRS;
import com.kshrd.khracer_api.Repository.UserRepository;
import com.kshrd.khracer_api.RestController.Message.InvalidRequestMessage;
import com.kshrd.khracer_api.RestController.Message.SuccessMessage;
import com.kshrd.khracer_api.RestController.RequestModel.HistoryRQ;
import com.kshrd.khracer_api.Service.HistoryService;
import org.apache.tomcat.util.http.parser.ContentRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImp implements HistoryService {
    private HistoryRepo historyRepo;
    private UserRepository userRepository;
    private IContentRepo iContentRepo;
    @Autowired
    public void setHistoryRepo(HistoryRepo historyRepo){
        this.historyRepo=historyRepo;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Autowired
    public void setIContentRepo(IContentRepo iContentRepo){
        this.iContentRepo=iContentRepo;
    }
    @Override
    public ResponseEntity<BaseApiResponse> postHistoryService(HistoryRQ historyRQ) {
        BaseApiResponse response= new BaseApiResponse();
        if(userRepository.selectUserId(historyRQ.getUserId())==null||
                historyRQ.getUserId()<1){
            response.setResponse(InvalidRequestMessage.USER_ID_UNAVAILABLE.value(),
                    true,
                    HttpStatus.BAD_REQUEST,
                    null
                    );
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        if(historyRQ.getWpm()>999||historyRQ.getWpm()<0){
            response.setResponse(InvalidRequestMessage.WPM_SIZE_ERROR.value(),
                    false,
                    HttpStatus.BAD_REQUEST,
                    null
                    );
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        if(historyRQ.getAccuracy()>=101||historyRQ.getAccuracy()<0){
            response.setResponse(InvalidRequestMessage.ACCURACY_SIZE_ERROR.value(),
                    false,
                    HttpStatus.BAD_REQUEST,
                    null
            );
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        if(historyRQ.getRank()>5||historyRQ.getRank()<1){
            response.setResponse(InvalidRequestMessage.RANK_SIZE_ERROR.value(),
                    false,
                    HttpStatus.BAD_REQUEST,
                    null
            );
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        if(iContentRepo.selectContentId(historyRQ.getContentId())==null||
                historyRQ.getContentId()<1){
            response.setResponse(InvalidRequestMessage.CONTENT_ID_UNAVAILABLE.value(),
                    true,
                    HttpStatus.BAD_REQUEST,
                    null
            );
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        if(!historyRepo.insertHistory(historyRQ)){
            response.setResponse(SuccessMessage.INSERT_HISTORY_UNSUCCESS.value(),
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setResponse(SuccessMessage.INSERT_HISTORY_SUCCESS.value(),
                true,
                HttpStatus.OK,
                null
                );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public List<UserHistoryRS> getUserHistories(int id, int limit, int page) {
        return historyRepo.selectUserHistories(id,limit,page);
    }

}
