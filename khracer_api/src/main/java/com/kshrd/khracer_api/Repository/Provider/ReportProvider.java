package com.kshrd.khracer_api.Repository.Provider;

import org.apache.ibatis.jdbc.SQL;

public class ReportProvider {


    public String updateReportSQL(){
        return  new SQL(){{
            UPDATE("reports");
            SET("is_read=#{read}");
            SET("is_disabled=#{disabled}");
            WHERE("report_id=#{id}");
        }}.toString();
    }

    public String insertReportSQL(){
        return new SQL(){{
            INSERT_INTO("reports");
            VALUES("user_id","#{id}");
            VALUES("report_content","#{reportText}");
        }}.toString();
    }
}
