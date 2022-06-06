package jobs.servlet;

import jobs.model.Candidate;
import jobs.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class CandidateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("candidates", Store.instOf().findAllCandidates());
        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Store.instOf().candidateSave(new Candidate(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("name"),
                req.getParameter("description"),
                LocalDate.parse(req.getParameter("created"))));
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}
