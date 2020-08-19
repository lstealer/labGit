package com.kshrd.khracer_api.RestController;

import com.kshrd.khracer_api.Repository.Model.BaseApiResponse;
import com.kshrd.khracer_api.Repository.Model.ReportRS;
import com.kshrd.khracer_api.Repository.Model.UserDRSM;
import com.kshrd.khracer_api.Repository.ReportRepository;
import com.kshrd.khracer_api.RestController.Message.InvalidRequestMessage;
import com.kshrd.khracer_api.RestController.Message.SuccessMessage;
import com.kshrd.khracer_api.RestController.RequestModel.ReportRQ;
import com.kshrd.khracer_api.RestController.RequestModel.ReportStatusRQ;
import com.kshrd.khracer_api.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/kh-racer/v1")

public class ReportController {
    private ReportService reportService;
    private ReportRepository reportRepository;
    @Autowired
    public void setReportRepository(ReportRepository reportRepository){
        this.reportRepository=reportRepository;
    }
    @Autowired
    public void setReportService(ReportService reportService){
        this.reportService=reportService;
    }
    //Admin
    //get all reports
    //only for undeleted message(isDisable=false)

    @GetMapping("/reports")
    ResponseEntity<BaseApiResponse<List<ReportRS>>> getAllReports(
            @RequestParam(value = "limit",defaultValue="5") int limit,
            @RequestParam(value = "page",defaultValue = "1")int page
    ){
        BaseApiResponse<List<ReportRS>> response=new BaseApiResponse<>();
        int recordCount=reportRepository.getAllReportCount();
        if(recordCount==0||!(page<=recordCount/limit+1)){
            response.setResponse(
                    InvalidRequestMessage.PAGE_NOT_AVAILABLE.value(),
                    true,
                    HttpStatus.NO_CONTENT,
                    null,
                    recordCount
            );
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        response.setResponse(
                SuccessMessage.GET_REPORTS_SUCCESS.value(),
                true,
                HttpStatus.OK,
                reportService.getAllReports(limit,(page-1)*limit),
                recordCount
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //for user report
    //for registered user only
    //report content can not be ""
    @PostMapping("/report")
    public ResponseEntity<BaseApiResponse> postReport(@RequestBody ReportRQ reportRQ){
        return reportService.postReport(reportRQ);
    }

    //Update status of report
    //is read or is delete
    @PatchMapping("/report")
    public ResponseEntity<BaseApiResponse> putReportStatus(@RequestBody ReportStatusRQ reportStatusRQ){
        return reportService.putReportStatus(reportStatusRQ);
    }
}
