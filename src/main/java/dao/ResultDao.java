package dao;

import model.Result;

import java.util.List;

public interface ResultDao extends Dao<Result> {

    List<Result> findByPollId(int pollId);

    Result findByScore(int pollId, int score);

}
