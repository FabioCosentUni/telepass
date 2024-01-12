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

/**
 * Servlet per la revoca di un transponder.
 */
public class RevokeTransponderServlet extends HttpServlet {

    private TransponderService transponderService;

    /**
     * Inizializza la servlet e crea un'istanza del servizio Transponder.
     *
     * @throws ServletException se si verifica un errore durante l'inizializzazione.
     */
    @Override
    public void init() throws ServletException {
        this.transponderService = new TransponderServiceImpl(new TransponderHibernateDAOImpl());
    }

    /**
     * Gestisce le richieste GET per la visualizzazione della pagina di revoca contenenete la lista dei transponder attivi.
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
        if(!PermissionFilter.checkAdmin(u)) {
            req.setAttribute("error", TelepassError.VIEW_NOT_PERMITTED);
            req.getServletContext().getRequestDispatcher("/revokeTransponder.jsp").forward(req, resp);
            return;
        }

        //Recupero la lista dei transponder attivi
        List<Transponder> transponderList = null;
        try {
            transponderList = transponderService.getActiveTransponders();
        } catch (TelepassException e) {
            req.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(req, resp);
        }

        req.setAttribute("transponders", transponderList);

        //Reindirizzo alla pagina di revoca
        req.getServletContext().getRequestDispatcher("/revokeTransponder.jsp").forward(req, resp);
    }

    /**
     * Gestisce le richieste PUT per la revoca di un transponder.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException se si verifica un errore durante la gestione della richiesta.
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta.
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String transponderCode = req.getParameter("code");

        try {
            //revoca il transponder selezionato
            transponderService.revokeTransponder(transponderCode);
        } catch (TelepassException e) {
            //se si verifica un errore generico, rimando alla pagina di errore
            e.printStackTrace();
            req.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(req, resp);
        }
    }
}
