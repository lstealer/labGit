package com.kshrd.khracer_api.Service;

import com.kshrd.khracer_api.Repository.Model.BaseApiResponse;
import com.kshrd.khracer_api.Repository.Model.UserHistoryRS;
import com.kshrd.khracer_api.RestController.RequestModel.HistoryRQ;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HistoryService {

    ResponseEntity<BaseApiResponse> postHistoryService(HistoryRQ historyRQ);

    List<UserHistoryRS> getUserHistories(int id, int limit, int page);
}
