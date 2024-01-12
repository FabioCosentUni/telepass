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
 * Servlet per la gestione del login degli utenti.
 */
public class LoginServlet extends HttpServlet {

    private UtenteService utenteService;

    /**
     * Inizializza la servlet e istanzia i servizi necessari.
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
     * Gestisce le richieste POST per il login degli utenti.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException se si verifica un errore durante la gestione della richiesta.
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cf = request.getParameter("codice_fiscale");
        String password = request.getParameter("password");
        Utente u;
        try {
            u = utenteService.login(cf, password);
            request.getSession().setAttribute("utente", u);

            //se l'utente non ha ancora registrato un veicolo al transponder viene reindirizzato alla pagina di assegnazione
            if (u.getTransponder() != null && u.getAmministratore() == 0 && u.getTransponder().getVeicoloList().isEmpty()) {
                request.getServletContext().getRequestDispatcher("/assignVehicle.jsp").forward(request, response);
                return;
            }

            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (TelepassException e) {

            //se l'errore è generico viene visualizzata la pagina di errore
            if (TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                e.printStackTrace();
                request.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
                return;
            }

            // altrimenti viene visualizzata la pagina di login con l'errore (cf o password errati)
            request.setAttribute("error", e.getErrorCause());
            request.setAttribute("codice_fiscale", request.getParameter("codice_fiscale"));
            request.setAttribute("password", request.getParameter("password"));
            request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    /**
     * Gestisce le richieste GET per la visualizzazione della pagina di login.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException se si verifica un errore durante la gestione della richiesta.
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
