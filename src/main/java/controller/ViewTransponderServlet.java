package controller;

import dao.impl.CaselloHibernateDAOImpl;
import dao.impl.VeicoloHibernateDAOImpl;
import dao.impl.ViaggioHibernateDAOImpl;
import exception.TelepassError;
import exception.TelepassException;
import model.Utente;
import model.Veicolo;
import model.Viaggio;
import service.VeicoloService;
import service.ViaggioService;
import service.impl.VeicoloServiceImpl;
import service.impl.ViaggioServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Servlet per la visualizzazione dei transponder dell'utente e dei relativi viaggi.
 */
public class ViewTransponderServlet extends HttpServlet {

    private VeicoloService veicoloService;
    private ViaggioService viaggioService;

    /**
     * Inizializza la servlet e crea un'istanza dei servizi Veicolo e Viaggio.
     *
     * @throws ServletException se si verifica un errore durante l'inizializzazione.
     */
    @Override
    public void init() throws ServletException {
        try {
            super.init();
            veicoloService = new VeicoloServiceImpl(new VeicoloHibernateDAOImpl());
            viaggioService = new ViaggioServiceImpl(new ViaggioHibernateDAOImpl(), new CaselloHibernateDAOImpl(), new VeicoloHibernateDAOImpl());
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /**
     * Gestisce le richieste GET per visualizzare i transponder dell'utente e i relativi viaggi.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException se si verifica un errore durante la gestione della richiesta.
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Utente u = (Utente) req.getSession().getAttribute("utente");

            //Recupera i veicoli dell'utente
            List<Veicolo> veicoli = veicoloService.getVeicoliUtente(u);

            //Recupera i viaggi per ogni veicolo
            Map<Veicolo, List<Viaggio>> viaggiMap = viaggioService.getViaggiPerVeicoli(veicoli);
            req.setAttribute("viaggiMap", viaggiMap);

            //Visualizza la pagina
            req.getServletContext().getRequestDispatcher("/viewTransponder.jsp").forward(req, resp);

        } catch (TelepassException e) {
            e.printStackTrace();

            if (TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                req.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(req, resp);
            }
        }
    }
}
