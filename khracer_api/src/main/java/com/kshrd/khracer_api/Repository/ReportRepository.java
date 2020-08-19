package com.kshrd.khracer_api.Repository;

import com.kshrd.khracer_api.Repository.Model.BaseApiResponse;
import com.kshrd.khracer_api.Repository.Model.ReportRS;
import com.kshrd.khracer_api.Repository.Provider.ReportProvider;
import com.kshrd.khracer_api.RestController.RequestModel.ReportRQ;
import com.kshrd.khracer_api.RestController.RequestModel.ReportStatusRQ;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReportRepository {
    //count total report that not deleted
    @Select("SELECT COUNT(*) FROM reports WHERE is_disabled = false")
    public int getAllReportCount();

    @Select("SELECT r.report_id,r.user_id,r.create_at,r.report_content,r.is_read,u.user_name,u.user_email FROM reports r INNER JOIN users u ON u.user_id = r.user_id WHERE r.is_disabled =false;")
    @Result(property = "id",column = "report_id")
    @Result(property = "userId", column = "user_id")
    @Result(property = "date",column = "create_at")
    @Result(property = "reportText",column = "report_content")
    @Result(property = "is_read",column = "isRead")
    @Result(property = "name",column = "user_name")
    @Result(property = "email",column = "user_email")
    public List<ReportRS> getAllReports();

    @InsertProvider(value = ReportProvider.class,method = "insertReportSQL")
    boolean insertReport(ReportRQ reportRQ);


    @Select("SELECT report_id FROM reports WHERE report_id=#{id}")
    String getReportsId(int id);

//    @Update("UPDATE reports set is_disabled=#{disabled},is_read=#{read} where report_id=#{id}")
    @UpdateProvider(value = ReportProvider.class,method = "updateReportSQL")
    boolean updateReport(ReportStatusRQ reportStatusRQ);
}
