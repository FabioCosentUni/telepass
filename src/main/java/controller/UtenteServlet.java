package controller;

import exception.user.UserError;
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
import java.sql.SQLException;

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
                try {
                    handleLogin(request, response);
                } catch (SQLException e) {
                    // restituire una pagina d'errore ?
                }
                break;
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Utente u = utenteService.login(email, password);
            //mettere in sessione?
        } catch (UserException e) {
            if(UserError.INCORRECT_EMAIL.equals(e.getErrorCause())) {
                // che fare?
            } else if(UserError.INCORRECT_PASSWORD.equals(e.getErrorCause())) {
                // che fare?
            }
        }
    }

}
