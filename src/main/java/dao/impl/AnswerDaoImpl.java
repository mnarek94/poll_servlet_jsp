package dao.impl;

import dao.AnswerDao;
import dao.db.DBConnectionProvider;
import model.Answer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoImpl implements AnswerDao {
    private final Connection connection = DBConnectionProvider.getDbConnectionProvider().getConnection();

    @Override
    public List<Answer> findByQuestionId(int questionId) {
        List<Answer> answerList = new ArrayList<>();
        String query = "SELECT * FROM answer WHERE question_id = "  + questionId ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Answer answer = Answer.builder()
                        .id(resultSet.getInt(1))
                        .text(resultSet.getString(2))
                        .weight(resultSet.getInt(1))
                        .build();
                answerList.add(answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answerList;
    }

    @Override
    public List<Answer> findAll() {
        List<Answer> answerList = new ArrayList<>();
        String query = "SELECT * FROM answer";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Answer question = Answer.builder()
                        .id(resultSet.getInt(1))
                        .text(resultSet.getString(2))
                        .weight(resultSet.getInt(3))
                        .build();
                answerList.add(question);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return answerList;
    }

    @Override
    public Answer findById(int id) {

        String query = "SELECT * FROM answer WHERE id = " + id ;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Answer.builder()
                        .id(resultSet.getInt(1))
                        .text(resultSet.getString(2))
                        .weight(resultSet.getInt(3))
                        .build();
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;

    }
}
