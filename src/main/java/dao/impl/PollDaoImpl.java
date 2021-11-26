package dao.impl;

import dao.Dao;
import dao.db.DBConnectionProvider;
import model.Poll;
import model.Question;
import model.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PollDaoImpl implements Dao {
    private final Connection connection = DBConnectionProvider.getDbConnectionProvider().getConnection();
    private final QuestionDaoImpl questionDao = new QuestionDaoImpl();
    private final ResultDaoImpl resultDao = new ResultDaoImpl();

    @Override
    public List<Poll> findAll() {
        List<Poll> pollList = new ArrayList<>();
        String query = "SELECT * FROM poll";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Poll poll = Poll.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .questions(questionDao.findByPollId(resultSet.getInt(1)))
                        .results(resultDao.findByPollId(resultSet.getInt(1)))
                        .build();
                pollList.add(poll);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pollList;
    }

    @Override
    public Poll findById(int id) {
        String query = "SELECT * FROM poll WHERE id = " + "'" + id + "'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Poll.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .questions(questionDao.findByPollId(id))
                        .results(resultDao.findByPollId(id))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
