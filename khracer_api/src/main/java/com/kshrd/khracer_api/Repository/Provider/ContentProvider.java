package com.kshrd.khracer_api.Repository.Provider;

import org.apache.ibatis.jdbc.SQL;

public class ContentProvider {
    public String SelectRandomContentSQL() {
        return new SQL() {
            {
                SELECT("*");
                FROM("typing_contents");
                ORDER_BY("random()");
                LIMIT(1);
            }
        }.toString();
    }
    public String InsertContentSQL(){
        return new SQL(){{
            INSERT_INTO("typing_contents");
            VALUES("kh_content","#{khContent}");
            VALUES("content_title","#{title}");
            VALUES("content_desciption","#{description}");
            VALUES("content_author","#{author}");
            VALUES("content_image","#{image}");
        }}.toString();
    }
    public String DeleteContentSQL(){
        return new SQL(){{
            DELETE_FROM("typing_contents");
            WHERE("content_id=#{id}");
        }}.toString();
    }
    public String UpdateKHContentSQL(){
        return new SQL(){{
            UPDATE("typing_contents");
            SET("kh_content=#{khContent}");
            WHERE("content_id=#{id}");
        }}.toString();
    }
}
