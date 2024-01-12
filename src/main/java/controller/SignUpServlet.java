package controller;

import dao.impl.TransponderHibernateDAOImpl;
import dao.impl.UtenteHibernateDAOImpl;
import exception.TelepassError;
import exception.TelepassException;
import model.Utente;
import service.UtenteService;
import service.impl.UtenteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet per la gestione delle richieste di registrazione di un nuovo utente.
 */
public class SignUpServlet extends HttpServlet {
    private UtenteService utenteService;

    /**
     * Inizializza la servlet e crea un'istanza del servizio Utente.
     *
     * @throws ServletException se si verifica un errore durante l'inizializzazione.
     */
    public void init() {
        try {
            super.init();
            utenteService = new UtenteServiceImpl(new UtenteHibernateDAOImpl(), new TransponderHibernateDAOImpl());
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gestisce le richieste POST per il primo step di registrazione di un nuovo utente.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException se si verifica un errore durante la gestione della richiesta.
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Utente u = new Utente(
                    request.getParameter("codice_fiscale"),
                    request.getParameter("name"),
                    request.getParameter("email"),
                    request.getParameter("surname"),
                    request.getParameter("sesso").charAt(0),
                    request.getParameter("password")
            );

            // Validazione dell'utente. Controllo se il codice fiscale o l'email sono già presenti nel database
            utenteService.validateUser(u);

            request.getSession().setAttribute("utenteProv", u);
            request.getServletContext().getRequestDispatcher("/assignVehicle.jsp").forward(request, response);
        } catch (TelepassException e) {
            e.printStackTrace();

            // Se si verifica un errore generico, reindirizzo alla pagina di errore
            if(TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                request.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
                return;
            }

            // Altrimenti reindirizzo alla pagina di registrazione con i campi già compilati e l'errore

            request.setAttribute("error", e.getErrorCause());
            request.setAttribute("codice_fiscale", request.getParameter("codice_fiscale"));
            request.setAttribute("name", request.getParameter("name"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("surname", request.getParameter("surname"));
            request.setAttribute("sesso", request.getParameter("sesso"));
            request.setAttribute("password", request.getParameter("password"));
            request.getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
        }
    }

    /**
     * Gestisce le richieste GET per la visualizzazione della pagina di registrazione.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException se si verifica un errore durante la gestione della richiesta.
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
    }
}
