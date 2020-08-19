package com.kshrd.khracer_api.Repository.Provider;

import org.apache.ibatis.jdbc.SQL;

public class HistoryProvider {
    public String insertHistorySQL(){
        return new SQL(){{
            INSERT_INTO("match_history");
            VALUES("user_id","#{userId}");
            VALUES("content_id","#{contentId}");
            VALUES("match_wpm","#{wpm}");
            VALUES("match_rank","#{rank}");
            VALUES("accuracy","#{accuracy}");
        }}.toString();
    }
    public String selectUserHistoriesSQL(){
        return new SQL(){{
            SELECT("user_id,match_date,match_wpm,match_rank,accuracy");
            FROM("match_history");
            WHERE("user_id=#{id}");
            OFFSET("#{page}");
            LIMIT("#{limit}");
        }}.toString();
    }
}
