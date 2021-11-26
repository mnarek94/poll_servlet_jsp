package dao.impl;

import dao.ResultDao;
import dao.db.DBConnectionProvider;
import model.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultDaoImpl implements ResultDao {
    private final Connection connection = DBConnectionProvider.getDbConnectionProvider().getConnection();

    @Override
    public List<Result> findAll() {
        String query = "SELECT *FROM result";
        List<Result> resultList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Result result = Result.builder()
                        .id(resultSet.getInt(1))
                        .explanation(resultSet.getString(2))
                        .minScore(resultSet.getInt(3))
                        .maxScore(resultSet.getInt(4))
                        .build();
                resultList.add(result);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public Result findById(int id) {
        String query = "SELECT * FROM result WHERE id=" + id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Result.builder()
                        .id(resultSet.getInt(1))
                        .explanation(resultSet.getString(2))
                        .minScore(resultSet.getInt(3))
                        .maxScore(resultSet.getInt(4))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Result> findByPollId(int pollId) {
        List<Result> resultList = new ArrayList<>();
        String query = "SELECT*FROM result WHERE poll_id =" + pollId;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Result result = Result.builder()
                        .id(resultSet.getInt(1))
                        .explanation(resultSet.getString(2))
                        .minScore(resultSet.getInt(3))
                        .maxScore(resultSet.getInt(4))
                        .build();
                resultList.add(result);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public Result findByScore(int pollId, int score) {

        String query = "SELECT*FROM result WHERE poll_id =" + pollId + " and min_score <=" + score + " and max_score>=" + score;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Result.builder()
                        .id(resultSet.getInt(1))
                        .explanation(resultSet.getString(2))
                        .minScore(resultSet.getInt(3))
                        .maxScore(resultSet.getInt(4))
                        .build();


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
