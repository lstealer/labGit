package com.kshrd.khracer_api.Service;

import com.kshrd.khracer_api.Repository.Model.BaseApiResponse;
import com.kshrd.khracer_api.Repository.Model.ReportRS;
import com.kshrd.khracer_api.RestController.RequestModel.ReportRQ;
import com.kshrd.khracer_api.RestController.RequestModel.ReportStatusRQ;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReportService {
    public List<ReportRS> getAllReports(int limit,int page);

    ResponseEntity<BaseApiResponse> postReport(ReportRQ reportRQ);

    ResponseEntity<BaseApiResponse> putReportStatus(ReportStatusRQ reportStatusRQ);
}
