package com.kshrd.khracer_api.Repository.Provider;

import org.apache.ibatis.jdbc.SQL;

public class HomeProvider {
    public String selectTopIDSQL() {
        return new SQL(){{
            SELECT("User_ID");
            FROM("Users");
            ORDER_BY("user_top_score desc");
            LIMIT("#{limit}");
        }}.toString();
    }
    public String selectTopUserDetailSQL(){
        return new SQL(){{
            SELECT("match_wpm,accuracy,match_date");
            FROM("match_history");
            ORDER_BY("match_wpm desc,accuracy desc");
            WHERE("User_ID=#{id}");
            LIMIT(1);
        }}.toString();
    }
}
