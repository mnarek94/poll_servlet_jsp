package dao;
import model.Question;
import java.util.List;

public interface QuestionDao extends Dao<Question> {

    List<Question> findByPollId(int pollId);

}
