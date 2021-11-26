package servlet;

import dao.impl.QuestionDaoImpl;
import dao.impl.ResultDaoImpl;
import model.Question;
import model.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/result")
public class ResultServlet extends HttpServlet {
    private final QuestionDaoImpl questionDao = new QuestionDaoImpl();
    private final ResultDaoImpl resultDao = new ResultDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pollId = req.getParameter("pollId");
        if (pollId == null || pollId.trim().isEmpty()) {
            resp.sendRedirect("/question");

        } else {
            int score = 0;
            for (Question question : questionDao.findAll()) {
                String weight = req.getParameter(""+question.getId());
                if(weight!=null){
                score += Integer.parseInt(weight);
            }}
            Result result = resultDao.findByScore(Integer.parseInt(pollId), score);
            req.setAttribute("result",result);
            req.getRequestDispatcher("result.jsp").forward(req,resp);
        }
    }
}
