package controller;

import exception.user.UserException;
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
                    request.getParameter("password"),
                    request.getParameter("address"),
                    request.getParameter("city"),
                    request.getParameter("region")
            );

            utenteService.register(u);

            request.getSession().setAttribute("utente", u);
            request.getServletContext().getRequestDispatcher("/assignVehicle.jsp").forward(request, response);
        } catch (UserException e) {
            e.printStackTrace();
            request.setAttribute("error", e.getErrorCause());
            request.setAttribute("codice_fiscale", request.getParameter("codice_fiscale"));
            request.setAttribute("name", request.getParameter("name"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("surname", request.getParameter("surname"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("password", request.getParameter("password"));
            request.setAttribute("address", request.getParameter("address"));
            request.setAttribute("city", request.getParameter("city"));
            request.setAttribute("region", request.getParameter("region"));
            request.getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
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
