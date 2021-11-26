package servlet;

import dao.impl.AnswerDaoImpl;
import dao.impl.PollDaoImpl;
import dao.impl.QuestionDaoImpl;
import model.Poll;
import model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/question")
public class QuestionServlet extends HttpServlet {
    private final QuestionDaoImpl questionDao = new QuestionDaoImpl();
    private final PollDaoImpl pollDao = new PollDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id == null || id.trim().isEmpty()){
            resp.sendRedirect("/poll");
        }else {
        Poll poll = pollDao.findById(Integer.parseInt(id));
        List<Question> questions = questionDao.findByPollId(Integer.parseInt(id));
        req.setAttribute("poll", poll);
        req.setAttribute("question", questions);
        req.getRequestDispatcher("question.jsp").forward(req, resp);
    }}
}
