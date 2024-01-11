package controller;

import command.impl.GetTariffCommandExecutorImpl;
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

public class GestisciAbbServlet extends HttpServlet {

    private ViaggioService viaggioService;
    private UtenteService utenteService;

    public void init() {
        try {
            super.init();
            viaggioService = new ViaggioServiceImpl(new ViaggioHibernateDAOImpl(), new CaselloHibernateDAOImpl(), new VeicoloHibernateDAOImpl(), new GetTariffCommandExecutorImpl(new AutostradaDAOImpl()));
            utenteService = new UtenteServiceImpl(new UtenteHibernateDAOImpl(), new TransponderHibernateDAOImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        try {
            utente = utenteService.getUtenteByCF(utente.getCodiceFiscalePk());

            if (utente == null || utente.getTransponder() == null) {
                //L'amministratore ha revocato il transponder all'utente
                throw new TelepassException(TelepassError.TRANSPONDER_REVOKED);
            }

            //Aggiorno l'utente in sessione
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
            if (TelepassError.TRANSPONDER_REVOKED.equals(e.getErrorCause())) {
                request.setAttribute("error", e.getErrorCause());
                request.getServletContext().getRequestDispatcher("/gestisciAbb.jsp").forward(request, response);
                return;
            }

            if (TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                request.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
            }

        }
    }

}
