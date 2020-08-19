package com.kshrd.khracer_api.Service.Imp;

import com.kshrd.khracer_api.Repository.Model.BaseApiResponse;
import com.kshrd.khracer_api.Repository.Model.ReportRS;
import com.kshrd.khracer_api.Repository.ReportRepository;
import com.kshrd.khracer_api.Repository.UserRepository;
import com.kshrd.khracer_api.RestController.Message.InvalidRequestMessage;
import com.kshrd.khracer_api.RestController.Message.SuccessMessage;
import com.kshrd.khracer_api.RestController.RequestModel.ReportRQ;
import com.kshrd.khracer_api.RestController.RequestModel.ReportStatusRQ;
import com.kshrd.khracer_api.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    ReportRepository reportRepository;
    UserRepository userRepository;

    @Autowired
    void setReportRepository(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Autowired
    void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<ReportRS> getAllReports(int limit, int page) {
        return reportRepository.getAllReports();
    }

    @Override
    public ResponseEntity<BaseApiResponse> postReport(ReportRQ reportRQ) {
        BaseApiResponse response = new BaseApiResponse();
        if (userRepository.selectUserId(reportRQ.getId()) == null) {
            response.setResponse(InvalidRequestMessage.USER_NOT_AVAILABLE.value(),
                    true,
                    HttpStatus.BAD_REQUEST,
                    null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (reportRQ.getReportText() == null || reportRQ.getReportText().equals("")) {
            response.setResponse(InvalidRequestMessage.REPORT_ERROR.value(),
                    true,
                    HttpStatus.BAD_REQUEST,
                    null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (reportRepository.insertReport(reportRQ)) {
            response.setResponse(SuccessMessage.INSERT_REPORT_SUCCESS.value(),
                    true,
                    HttpStatus.OK,
                    null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.setResponse(SuccessMessage.INSERT_REPORT_UNSUCESS.value(),
                true,
                HttpStatus.INTERNAL_SERVER_ERROR,
                null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<BaseApiResponse> putReportStatus(ReportStatusRQ reportStatusRQ) {
        BaseApiResponse response = new BaseApiResponse();
        if (reportRepository.getReportsId(reportStatusRQ.getId()) == null) {
            response.setResponse(InvalidRequestMessage.REPORT_UNAVAILABLE.value(),
                    true,
                    HttpStatus.BAD_REQUEST,
                    null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (reportRepository.updateReport(reportStatusRQ)) {
            response.setResponse(SuccessMessage.UPDATE_REPORT_SUCCESS.value(),
                    true,
                    HttpStatus.OK,
                    null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.setResponse(SuccessMessage.UPDATE_REPORT_UNSUCESS.value(),
                true,
                HttpStatus.INTERNAL_SERVER_ERROR,
                null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
