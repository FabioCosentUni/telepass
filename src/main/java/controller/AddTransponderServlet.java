package controller;

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

public class AddTransponderServlet extends HttpServlet {

    private TransponderService transponderService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.transponderService = new TransponderServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente u = (Utente) req.getSession().getAttribute("utente");

        if(!PermissionFilter.checkAdmin(u)) {
            req.setAttribute("error", TelepassError.VIEW_NOT_PERMITTED);
            req.getServletContext().getRequestDispatcher("/addTransponder.jsp").forward(req, resp);
            return;
        }

        req.getServletContext().getRequestDispatcher("/addTransponder.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Transponder transponder = new Transponder(
                req.getParameter("codice_transponder"),
                null,
                0
        );

        try {
            transponderService.insert(transponder);

            req.setAttribute("success", "Transponder aggiunto con successo");
            req.setAttribute("codice_transponder", transponder.getCodiceTransponder());
            req.getServletContext().getRequestDispatcher("/addTransponder.jsp").forward(req, resp);
        } catch (TelepassException e) {
            e.printStackTrace();
            if(TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                req.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(req, resp);
                return;
            }
            req.setAttribute("transponderError", e.getErrorCause());
            req.getServletContext().getRequestDispatcher("/addTransponder.jsp").forward(req, resp);
        }
    }
}
