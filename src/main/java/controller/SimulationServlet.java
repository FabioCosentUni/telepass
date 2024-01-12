package controller;

import dao.impl.CaselloHibernateDAOImpl;
import dao.impl.VeicoloHibernateDAOImpl;
import dao.impl.ViaggioHibernateDAOImpl;
import exception.TelepassError;
import exception.TelepassException;
import model.Casello;
import service.CaselloService;
import service.ViaggioService;
import service.impl.CaselloServiceImpl;
import service.impl.ViaggioServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet per la gestione delle simulazioni di viaggio.
 */
public class SimulationServlet extends HttpServlet {

    private ViaggioService viaggioService;
    private CaselloService caselloService;

    /**
     * Inizializza la servlet e crea un'istanza dei servizi Viaggio e Casello.
     *
     * @throws ServletException se si verifica un errore durante l'inizializzazione.
     */
    public void init() {
        try {
            super.init();
            viaggioService = new ViaggioServiceImpl(new ViaggioHibernateDAOImpl(), new CaselloHibernateDAOImpl(), new VeicoloHibernateDAOImpl());
            caselloService = new CaselloServiceImpl(new CaselloHibernateDAOImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gestisce le richieste POST per la simulazione di un viaggio.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException se si verifica un errore durante la gestione della richiesta.
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targaVeicolo = request.getParameter("veicoloSel");

        Long idCaselloPartenza = Long.valueOf(request.getParameter("entrataSelect"));
        Long idCaselloArrivo = Long.valueOf(request.getParameter("uscitaSelect"));
        try {

            //inserisce il viaggio nel database
            viaggioService.insertViaggio(idCaselloPartenza, idCaselloArrivo, targaVeicolo);
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

        } catch (TelepassException e) {
            e.printStackTrace();

            //se si un errore generico viene visualizzata la pagina di errore
            if(TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                request.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

    /**
     * Gestisce le richieste GET per la visualizzazione della pagina di simulazione di viaggio.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException se si verifica un errore durante la gestione della richiesta.
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Casello> caselli;
            List<String> autostrade;
            String autostrada = request.getParameter("autostrada");

            //seleziona tutti i caselli e le autostrade dal database
            caselli = caselloService.getAllCaselli();
            if (autostrada != null && !autostrada.equals("Autostrada")) {
                request.setAttribute("autostradaSel", autostrada);
            }
            autostrade = caselloService.getAllAutostrade().getAutostrade();

            request.setAttribute("caselli", caselli);
            request.setAttribute("autostrade", autostrade);
            request.getServletContext().getRequestDispatcher("/simulation.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
