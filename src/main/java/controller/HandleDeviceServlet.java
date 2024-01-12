package controller;

import exception.TelepassError;
import model.Utente;
import utils.PermissionFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet per la gestione dei dispositivi da parte di un admin.
 */
public class HandleDeviceServlet extends HttpServlet {

    /**
     * Inizializza la servlet.
     *
     * @throws ServletException se si verifica un errore durante l'inizializzazione.
     */
    @Override
    public void init() throws ServletException {
        super.init();
    }

    /**
     * Gestisce le richieste GET per la visualizzazione della pagina di gestione dei dispositivi.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException se si verifica un errore durante la gestione della richiesta.
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente u = (Utente) req.getSession().getAttribute("utente");

        //Controllo dei permessi. La pagina può essere visualizzata solo dall'admin
        if (!PermissionFilter.checkAdmin(u)) {
            req.setAttribute("error", TelepassError.VIEW_NOT_PERMITTED);
            req.getServletContext().getRequestDispatcher("/handleDevice.jsp").forward(req, resp);
            return;
        }

        req.getServletContext().getRequestDispatcher("/handleDevice.jsp").forward(req, resp);
    }
}
