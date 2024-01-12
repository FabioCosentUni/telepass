package controller;

import dao.impl.*;
import exception.TelepassError;
import exception.TelepassException;
import model.Utente;
import model.Veicolo;
import service.UtenteService;
import service.ViaggioService;
import service.impl.UtenteServiceImpl;
import service.impl.ViaggioServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Servlet per la gestione degli abbonamenti dell'utente.
 */
public class GestisciAbbServlet extends HttpServlet {

    private ViaggioService viaggioService;
    private UtenteService utenteService;

    /**
     * Inizializza la servlet creando un'istanza dei servizi Viaggio e Utente.
     */
    public void init() {
        try {
            super.init();
            viaggioService = new ViaggioServiceImpl(new ViaggioHibernateDAOImpl(), new CaselloHibernateDAOImpl(), new VeicoloHibernateDAOImpl());
            utenteService = new UtenteServiceImpl(new UtenteHibernateDAOImpl(), new TransponderHibernateDAOImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gestisce le richieste GET per la visualizzazione delle informazioni sull'abbonamento dell'utente.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException se si verifica un errore durante la gestione della richiesta.
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        try {
            utente = utenteService.getUtenteByCF(utente.getCodiceFiscalePk());

            if (utente == null || utente.getTransponder() == null) {
                // L'amministratore ha revocato il transponder all'utente
                throw new TelepassException(TelepassError.TRANSPONDER_REVOKED);
            }

            // Aggiorno l'utente in sessione
            request.getSession().setAttribute("utente", utente);

            Map<Veicolo, Float> pedaggiViaggi;
            pedaggiViaggi = viaggioService.getImportoTotalePagatoPerVeicolo(utente);

            if (pedaggiViaggi == null) {
                throw new TelepassException(TelepassError.GENERIC_ERROR);
            } else {
                request.setAttribute("pedaggiViaggi", pedaggiViaggi);
            }

            request.getServletContext().getRequestDispatcher("/gestisciAbb.jsp").forward(request, response);

        } catch (TelepassException e) {
            //se il transponder è stato revocato dall'amministratore, setto l'errore e rimando alla pagina di gestione
            if (TelepassError.TRANSPONDER_REVOKED.equals(e.getErrorCause())) {
                request.setAttribute("error", e.getErrorCause());
                request.getServletContext().getRequestDispatcher("/gestisciAbb.jsp").forward(request, response);
                return;
            }

            //se si verifica un errore generico, rimando alla pagina di errore
            if (TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                request.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
            }
        }
    }
}
