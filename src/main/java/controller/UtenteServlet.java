package controller;

import exception.user.UserException;
import model.Utente;
import service.UtenteService;
import service.impl.UtenteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/*")
public class UtenteServlet extends HttpServlet {
    private UtenteService utenteService;

    public void init() {
        try{
            super.init();
            utenteService = new UtenteServiceImpl();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }
        switch (action){
            case "/":
                //utenteService.getAllUtenti().forEach(utente -> System.out.println(utente.getNome()));
                break;
            case "/login":
                request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                //utenteService.insertUtente();
                break;
            case "/insert":
                //utenteService.addUtente(request, response);
                break;
            case "/delete":
                //utenteService.deleteUtente(request, response);
                break;
            case "/edit":
                //utenteService.showEditForm(request, response);
                break;
            case "/update":
                //utenteService.updateUtente(request, response);
                break;
            default:
                //utenteService.getAllUtenti(request, response);
                break;
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        switch (action) {
            case "/login":
                handleLogin(request, response);
                break;
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Utente u = utenteService.login(email, password);
            request.getSession().setAttribute("utente", u);
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (UserException e) {
            request.setAttribute("error", e.getErrorCause());
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("password", request.getParameter("password"));
            request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, e.getMessage());
        }
    }

}
