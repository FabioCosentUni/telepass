package controller;

import dao.impl.MethodPaymentHibernateDAOImpl;
import dao.impl.TransponderHibernateDAOImpl;
import dao.impl.UtenteHibernateDAOImpl;
import exception.TelepassError;
import exception.TelepassException;
import model.MethodPayment;
import model.Utente;
import model.Veicolo;
import service.MethodPaymentService;
import service.UtenteService;
import service.impl.MethodPaymentServiceImpl;
import service.impl.UtenteServiceImpl;
import utils.PaymentOption;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Servlet per la gestione della registrazione di un utente e di un veicolo.
 */
public class RegisterServlet extends HttpServlet {

    private MethodPaymentService methodPaymentService;
    private UtenteService utenteService;

    /**
     * Inizializza la servlet.
     *
     * @throws ServletException se si verifica un errore durante l'inizializzazione.
     */
    public void init() {
        try {
            super.init();
            methodPaymentService = new MethodPaymentServiceImpl(new MethodPaymentHibernateDAOImpl());
            utenteService = new UtenteServiceImpl(new UtenteHibernateDAOImpl(), new TransponderHibernateDAOImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gestisce le richieste POST per la registrazione di un utente e di un veicolo.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException se si verifica un errore durante la gestione della richiesta.
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Utente u = (Utente) request.getSession().getAttribute("utenteProv");
            Veicolo v = (Veicolo) request.getSession().getAttribute("veicolo");
            PaymentOption p = PaymentOption.getPaymentById(Integer.parseInt(request.getParameter("paymentOption")));

            MethodPayment methodPayment = new MethodPayment(
                    request.getParameter("numero_carta"),
                    request.getParameter("nome_prp"),
                    request.getParameter("cognome_prp"),
                    Date.valueOf(request.getParameter("scadenza")),
                    request.getParameter("cvc"),
                    p.getName()
            );

            // Validazione del metodo di pagamento (metodo di pagamento già esistente o meno)
            methodPaymentService.validateMethodPayment(methodPayment);
            u.setMethodPayment(methodPayment);

            //registrazione dell'utente
            utenteService.register(u, v);
            request.getSession().setAttribute("utente", u);
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (TelepassException e) {
            e.printStackTrace();

            //Se si verifica un errore generico, reindirizzo alla pagina di errore
            if (TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                request.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
                return;
            }

            //Se non c'è nessun transponder associabile ad un utente, viene visualizzato un messaggio di errore e renderizzato alla pagina di index
            if (TelepassError.TRANSPONDER_NOT_AVAILABLE.equals(e.getErrorCause())) {

                request.setAttribute("error", e.getErrorCause() + " Appena disponibile verrai contattato");
                request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                return;
            }

            //Altrimenti viene visualizzata la pagina di registrazione con l'errore

            request.setAttribute("error", e.getErrorCause());
            request.setAttribute("numero_carta", request.getParameter("numero_carta"));
            request.setAttribute("nome_prp", request.getParameter("nome_prp"));
            request.setAttribute("cognome_prp", request.getParameter("cognome_prp"));
            request.setAttribute("scadenza", request.getParameter("scadenza"));
            request.setAttribute("cvc", request.getParameter("cvc"));
            request.getServletContext().getRequestDispatcher("/methodPayment.jsp").forward(request, response);
        }
    }
}

