package controller;

import exception.user.UserError;
import exception.user.UserException;
import model.MethodPayment;
import model.Utente;
import service.MethodPaymentService;
import service.impl.MethodPaymentServiceImpl;
import utils.PaymentOption;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class MethodPaymentServlet extends HttpServlet {
    private MethodPaymentService methodPaymentService;

    public void init() {
        try {
            super.init();
            methodPaymentService = new MethodPaymentServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PaymentOption p = PaymentOption.getPaymentById(Integer.parseInt(request.getParameter("paymentOption")));
            if(p == null) {
                throw new UserException(UserError.PAYMENT_OPTION_NOT_FOUND);
            }

            Utente u = (Utente) request.getSession().getAttribute("utente");
            MethodPayment m = new MethodPayment(
                    request.getParameter("numero_carta"),
                    request.getParameter("nome_prp"),
                    request.getParameter("cognome_prp"),
                    Date.valueOf(request.getParameter("scadenza")),
                    request.getParameter("cvc"),
                    p.getName()
            );

            methodPaymentService.saveMethodPayment(m, u);

            request.getSession().setAttribute("utente", u);
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (UserException e){
            e.printStackTrace();
            request.setAttribute("error", e.getErrorCause());
            request.setAttribute("numero_carta", request.getParameter("numero_carta"));
            request.setAttribute("nome_prp", request.getParameter("nome_prp"));
            request.setAttribute("cognome_prp", request.getParameter("cognome_prp"));
            request.setAttribute("scadenza", request.getParameter("scadenza"));
            request.setAttribute("cvc", request.getParameter("cvc"));
            request.getServletContext().getRequestDispatcher("/methodPayment.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, e.getMessage());
        }
    }

}
