package com.kshrd.khracer_api.Repository;

import com.kshrd.khracer_api.Repository.Model.UserHistoryRS;
import com.kshrd.khracer_api.Repository.Provider.HistoryProvider;
import com.kshrd.khracer_api.RestController.RequestModel.HistoryRQ;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepo {
    @InsertProvider(value = HistoryProvider.class,method = "insertHistorySQL")
    public boolean insertHistory(HistoryRQ historyRQ);
    @SelectProvider(value = HistoryProvider.class,method = "selectUserHistoriesSQL")
    @Result(column = "match_wpm",property = "wpm")
    @Result(column = "match_rank",property = "rank")
    @Result(column = "match_date",property = "date")
    @Result(column = "user_id",property = "id")
    List<UserHistoryRS> selectUserHistories(int id, int limit, int page);
}
