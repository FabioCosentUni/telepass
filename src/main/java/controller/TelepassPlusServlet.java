package controller;

import dao.impl.TransponderHibernateDAOImpl;
import dao.impl.UtenteHibernateDAOImpl;
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

/**
 * Servlet per la gestione dell'attivazione di Telepass Plus.
 */
public class TelepassPlusServlet extends HttpServlet {

    private UtenteService utenteService;
    private TransponderService transponderService;

    /**
     * Inizializza la servlet e crea un'istanza dei servizi Utente e Transponder.
     *
     * @throws ServletException se si verifica un errore durante l'inizializzazione.
     */
    @Override
    public void init() throws ServletException {
        utenteService = new UtenteServiceImpl(new UtenteHibernateDAOImpl(), new TransponderHibernateDAOImpl());
        transponderService = new TransponderServiceImpl(new TransponderHibernateDAOImpl());
    }

    /**
     * Gestisce le richieste GET per visualizzare la pagina di richiesta di Telepass Plus.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException se si verifica un errore durante la gestione della richiesta.
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/richiediTelepassPlus.jsp").forward(req, resp);
    }

    /**
     * Gestisce le richieste POST per l'attivazione di Telepass Plus.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException se si verifica un errore durante la gestione della richiesta.
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente u = (Utente) req.getSession().getAttribute("utente");

        try {
            u = utenteService.getUtenteByCF(u.getCodiceFiscalePk());

            //Se l'utente non ha il transponder perchè revocato, rimando alla pagina di errore
            if(u.getTransponder() == null) {
                throw new TelepassException(TelepassError.GENERIC_ERROR);
            }

            //Attivo il Telepass Plus
            transponderService.makePlus(u.getTransponder());

            req.getSession().setAttribute("utente", u);
            req.setAttribute("success", "Telepass Plus attivato con successo!");
            resp.sendRedirect(req.getContextPath() + "/gestisciAbb");
        } catch (TelepassException e) {
            e.printStackTrace();
            //Se si verifica un errore generico, reindirizzo alla pagina di errore
            if(TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                req.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(req, resp);
            }
        }
    }
}
