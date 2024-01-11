package controller;

import dao.impl.TransponderHibernateDAOImpl;
import exception.TelepassError;
import exception.TelepassException;
import model.Transponder;
import model.Utente;
import service.TransponderService;
import service.impl.TransponderServiceImpl;
import utils.PermissionFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RevokeTransponderServlet extends HttpServlet {

    private TransponderService transponderService;

    @Override
    public void init() throws ServletException {
        this.transponderService = new TransponderServiceImpl(new TransponderHibernateDAOImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente u = (Utente) req.getSession().getAttribute("utente");

        if(!PermissionFilter.checkAdmin(u)) {
            req.setAttribute("error", TelepassError.VIEW_NOT_PERMITTED);
            req.getServletContext().getRequestDispatcher("/revokeTransponder.jsp").forward(req, resp);
            return;
        }

        List<Transponder> transponderList = null;
        try {
            transponderList = transponderService.getActiveTransponders();
        } catch (TelepassException e) {
            req.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(req, resp);
        }

        req.setAttribute("transponders", transponderList);

        req.getServletContext().getRequestDispatcher("/revokeTransponder.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String transponderCode = req.getParameter("code");

        try {
            transponderService.revokeTransponder(transponderCode);
        } catch (TelepassException e) {
            req.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(req, resp);
        }

    }
}
