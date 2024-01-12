package controller;

import dao.impl.CaselloHibernateDAOImpl;
import dao.impl.VeicoloHibernateDAOImpl;
import dao.impl.ViaggioHibernateDAOImpl;
import exception.TelepassError;
import exception.TelepassException;
import model.Casello;
import model.Utente;
import model.bo.StatisticsBO;
import service.ViaggioService;
import service.impl.ViaggioServiceImpl;
import utils.PermissionFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Servlet per la visualizzazione delle statistiche sui caselli.
 */
public class ViewStatisticsServlet extends HttpServlet {

    private ViaggioService viaggioService;

    /**
     * Inizializza la servlet e crea un'istanza del servizio Viaggio.
     *
     * @throws ServletException se si verifica un errore durante l'inizializzazione.
     */
    public void init() {
        try {
            super.init();
            viaggioService = new ViaggioServiceImpl(new ViaggioHibernateDAOImpl(), new CaselloHibernateDAOImpl(), new VeicoloHibernateDAOImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gestisce le richieste GET per visualizzare le statistiche sui caselli.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException se si verifica un errore durante la gestione della richiesta.
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente u = (Utente) request.getSession().getAttribute("utente");

        //Check dei permessi. La pagina può essere visualizzata solo dall'admin
        if(!PermissionFilter.checkAdmin(u)) {
            request.setAttribute("error", TelepassError.VIEW_NOT_PERMITTED);
            request.getServletContext().getRequestDispatcher("/revokeTransponder.jsp").forward(request, response);
            return;
        }

        try {
            //Recupero le statistiche sui caselli
            Map<Casello, StatisticsBO> statistics = viaggioService.getStatisticheCaselli();

            request.setAttribute("statistics", statistics);
            request.getServletContext().getRequestDispatcher("/statistics.jsp").forward(request, response);
        } catch (TelepassException e) {
            //Se si verifica un errore generico viene visualizzata la pagina di errore
            e.printStackTrace();
            request.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }
}
