package controller;

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


public class SignUpServlet extends HttpServlet {
    private UtenteService utenteService;

    public void init() {
        try {
            super.init();
            utenteService = new UtenteServiceImpl();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Utente u = new Utente(
                    request.getParameter("codice_fiscale"),
                    request.getParameter("name"),
                    request.getParameter("email"),
                    request.getParameter("surname"),
                    request.getParameter("sesso").charAt(0),
                    request.getParameter("password")
            );

            utenteService.validateUser(u);

            request.getSession().setAttribute("utenteProv", u);
            request.getServletContext().getRequestDispatcher("/assignVehicle.jsp").forward(request, response);
        } catch (TelepassException e) {

            if(TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                //TODO handle default page error
                return;
            }

            request.setAttribute("error", e.getErrorCause());
            request.setAttribute("codice_fiscale", request.getParameter("codice_fiscale"));
            request.setAttribute("name", request.getParameter("name"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("surname", request.getParameter("surname"));
            request.setAttribute("sesso", request.getParameter("sesso"));
            request.setAttribute("password", request.getParameter("password"));
            request.getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
    }
}
