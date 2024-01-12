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

/**
 * Servlet per l'aggiunta di un nuovo transponder.
 */
public class AddTransponderServlet extends HttpServlet {

    private TransponderService transponderService;

    /**
     * Inizializza la servlet creando un'istanza del servizio Transponder.
     *
     * @throws ServletException se si verifica un errore durante l'inizializzazione.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        this.transponderService = new TransponderServiceImpl(new TransponderHibernateDAOImpl());
    }

    /**
     * Gestisce le richieste GET per la visualizzazione della pagina di aggiunta del transponder.
     * Operazione permessa solo all'admin.
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
            req.getServletContext().getRequestDispatcher("/addTransponder.jsp").forward(req, resp);
            return;
        }

        req.getServletContext().getRequestDispatcher("/addTransponder.jsp").forward(req, resp);
    }

    /**
     * Gestisce le richieste POST per l'aggiunta di un nuovo transponder.
     * Inserisce un nuovo transponder se il codice fornito non è già presente nel sistema, altrimenti visualizza un errore nella pagina.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException se si verifica un errore durante la gestione della richiesta.
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta.
     */
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
            if (TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                req.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(req, resp);
                return;
            }
            req.setAttribute("transponderError", e.getErrorCause());
            req.getServletContext().getRequestDispatcher("/addTransponder.jsp").forward(req, resp);
        }
    }
}
