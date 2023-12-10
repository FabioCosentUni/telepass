package controller;

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
                utenteService.getAllUtenti().forEach(utente -> System.out.println(utente.getNome()));
                break;
            case "/new":
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

}
