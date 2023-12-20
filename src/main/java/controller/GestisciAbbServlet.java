package controller;

import exception.TelepassError;
import exception.TelepassException;
import model.Utente;
import model.Veicolo;
import oracle.ucp.util.Pair;
import service.ViaggioService;
import service.impl.ViaggioServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestisciAbbServlet extends HttpServlet{

    private ViaggioService viaggioService;

    public void init() {
        try {
            super.init();
            viaggioService = new ViaggioServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        List<Pair<Veicolo, Integer>> pedaggiViaggi = new ArrayList<>();
        try {
            pedaggiViaggi = viaggioService.getImportoTotalePagatoPerVeicolo(utente);
            if(pedaggiViaggi == null) {
                throw new TelepassException(TelepassError.GENERIC_ERROR);
            } else {
                request.getSession().setAttribute("pedaggiViaggi", pedaggiViaggi);
            }
            request.getServletContext().getRequestDispatcher("/gestisciAbb.jsp").forward(request, response);
        } catch (TelepassException e) {
            throw new RuntimeException(e);
        }
    }

}
