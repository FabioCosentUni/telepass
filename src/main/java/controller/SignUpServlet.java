package controller;

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
            request.getSession().setAttribute("utenteProv", u);
            request.getServletContext().getRequestDispatcher("/assignVehicle.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, e.getMessage());
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
    }
}
