package servlet;

import dao.impl.PollDaoImpl;
import model.Poll;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/poll")
public class PollServlet extends HttpServlet {
    private final PollDaoImpl pollDao = new PollDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Poll> polls = pollDao.findAll();
        req.setAttribute("poll",polls);
        req.getRequestDispatcher("poll.jsp").forward(req,resp);

    }
}
