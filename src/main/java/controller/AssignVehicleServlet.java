package controller;

import dao.impl.TransponderHibernateDAOImpl;
import dao.impl.UtenteHibernateDAOImpl;
import dao.impl.VeicoloHibernateDAOImpl;
import exception.TelepassError;
import exception.TelepassException;
import model.Utente;
import model.Veicolo;
import service.UtenteService;
import service.VeicoloService;
import service.impl.UtenteServiceImpl;
import service.impl.VeicoloServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet per l'assegnazione di un veicolo a un utente.
 */
public class AssignVehicleServlet extends HttpServlet {

    private VeicoloService veicoloService;
    private UtenteService utenteService;

    /**
     * Inizializza la servlet creando un'istanza dei servizi Veicolo e Utente.
     */
    public void init() {
        try {
            super.init();
            veicoloService = new VeicoloServiceImpl(new VeicoloHibernateDAOImpl());
            utenteService = new UtenteServiceImpl(new UtenteHibernateDAOImpl(), new TransponderHibernateDAOImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gestisce le richieste POST per l'assegnazione di un veicolo a un utente.
     * Valida il veicolo e gestisce eventuali eccezioni.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException se si verifica un errore durante la gestione della richiesta.
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Utente u = (Utente) request.getSession().getAttribute("utente");
            Veicolo v = new Veicolo(
                    request.getParameter("targa_veicolo"),
                    request.getParameter("tipologia_veicolo"),
                    null
            );

            veicoloService.validateVeicolo(v);

            // Se l'utente è loggato e sta associando un nuovo veicolo al transponder
            if (u != null) {
                v.setTransponderDTO(u.getTransponder());
                u = utenteService.addVehicle(u, v);
                request.getSession().setAttribute("utente", u);

                response.sendRedirect(request.getContextPath() + "/gestisciAbb");
                return;
            }

            // Altrimenti passa alla fase successiva di registrazione
            request.getSession().setAttribute("veicolo", v);
            request.getServletContext().getRequestDispatcher("/methodPayment.jsp").forward(request, response);
        } catch (TelepassException e) {

            if (TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                request.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
                return;
            }

            request.setAttribute("error", e.getErrorCause());
            request.setAttribute("targa_veicolo", request.getParameter("targa_veicolo"));
            request.setAttribute("tipologia_veicolo", request.getParameter("tipologia"));
            request.getServletContext().getRequestDispatcher("/assignVehicle.jsp").forward(request, response);
        }
    }

    /**
     * Gestisce le richieste GET per la visualizzazione della pagina di assegnazione del veicolo.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException se si verifica un errore durante la gestione della richiesta.
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/assignVehicle.jsp").forward(request, response);
    }
}
