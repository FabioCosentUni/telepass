package controller;

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

public class RegisterServlet extends HttpServlet {

    private MethodPaymentService methodPaymentService;
    private UtenteService utenteService;

    public void init() {
        try {
            super.init();
            methodPaymentService = new MethodPaymentServiceImpl();
            utenteService = new UtenteServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

            methodPaymentService.validateMethodPayment(methodPayment);
            u.setMethodPayment(methodPayment);

            utenteService.register(u, v);
            request.getSession().setAttribute("utente", u);
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (TelepassException e) {

            if (TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                //TODO handle default page error
                //Rimandare all'index con messaggio di errore generico e invitare a riprovare
                return;
            }

            //TODO handle default trasponder not avaiabile page.
            if(TelepassError.TRANSPONDER_NOT_AVAILABLE.equals(e.getErrorCause())){
                //TODO handle TRANSPONDER_NOT_AVAILABLE page
                //Informare il cliente con un messaggio che non appena ci sarà disponibilità verrà avvisato per email
                return;
            }

            request.setAttribute("error", e.getErrorCause());
            request.setAttribute("numero_carta", request.getParameter("numero_carta"));
            request.setAttribute("nome_prp", request.getParameter("nome_prp"));
            request.setAttribute("cognome_prp", request.getParameter("cognome_prp"));
            request.setAttribute("scadenza", request.getParameter("scadenza"));
            request.setAttribute("cvc", request.getParameter("cvc"));
            request.getServletContext().getRequestDispatcher("/methodPayment.jsp").forward(request, response);
        } catch (Exception e) {
            //TODO DEFAULT PAGE
            //Rimandare all'index con messaggio di errore generico e invitare a riprovare
        }
    }

}
