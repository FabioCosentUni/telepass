package controller;

import exception.TelepassError;
import exception.TelepassException;
import model.Casello;
import model.Utente;
import model.bo.StatisticsBO;
import service.ViaggioService;
import service.impl.ViaggioServiceImpl;
import utils.PermissionFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ViewStatisticsServlet extends HttpServlet {

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

        Utente u = (Utente) request.getSession().getAttribute("utente");

        if(!PermissionFilter.checkAdmin(u)) {
            request.setAttribute("error", TelepassError.VIEW_NOT_PERMITTED);
            request.getServletContext().getRequestDispatcher("/revokeTransponder.jsp").forward(request, response);
            return;
        }

        try {
            Map<Casello, StatisticsBO> statistics = viaggioService.getStatisticheCaselli();
            request.setAttribute("statistics", statistics);
            request.getServletContext().getRequestDispatcher("/statistics.jsp").forward(request, response);
        } catch (TelepassException e) {
            request.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }


}
