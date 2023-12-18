package controller;

import exception.TelepassException;
import model.Utente;
import service.UtenteService;
import service.impl.UtenteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UtenteService utenteService;
    public void init() {
        try{
            super.init();
            utenteService = new UtenteServiceImpl();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cf = request.getParameter("codice_fiscale");
        String password = request.getParameter("password");
        Utente u;
        try {
            u = utenteService.login(cf, password);
            request.getSession().setAttribute("utente", u);

            if(u.getTransponder() != null && u.getTransponder().getVeicoloList().isEmpty()) {
                request.getServletContext().getRequestDispatcher("/assignVehicle.jsp").forward(request, response);
                return;
            }

            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (TelepassException e) {
            request.setAttribute("error", e.getErrorCause());
            request.setAttribute("codice_fiscale", request.getParameter("codice_fiscale"));
            request.setAttribute("password", request.getParameter("password"));
            request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, e.getMessage());
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
