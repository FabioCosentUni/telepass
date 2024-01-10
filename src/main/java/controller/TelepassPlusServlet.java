package controller;

import exception.TelepassError;
import exception.TelepassException;
import model.Utente;
import service.TransponderService;
import service.UtenteService;
import service.impl.TransponderServiceImpl;
import service.impl.UtenteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TelepassPlusServlet extends HttpServlet {

    private UtenteService utenteService;
    private TransponderService transponderService;

    @Override
    public void init() throws ServletException {
        utenteService = new UtenteServiceImpl();
        transponderService = new TransponderServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/richiediTelepassPlus.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente u = (Utente) req.getSession().getAttribute("utente");

        try {
            u = utenteService.getUtenteByCF(u.getCodiceFiscalePk());

            if(u.getTransponder() == null) {
                throw new TelepassException(TelepassError.GENERIC_ERROR);
            }

            transponderService.makePlus(u.getTransponder());

            req.getSession().setAttribute("utente", u);
            req.setAttribute("success", "Telepass Plus attivato con successo!");
            resp.sendRedirect(req.getContextPath() + "/gestisciAbb");
        } catch (TelepassException e) {
            if(TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                req.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(req, resp);
            }
        }
    }
}
