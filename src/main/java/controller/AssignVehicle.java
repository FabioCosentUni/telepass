package controller;

import model.Veicolo;
import service.VeicoloService;
import service.impl.VeicoloServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AssignVehicle extends HttpServlet{

    private VeicoloService veicoloService;

    public void init() {
        try {
            super.init();
            veicoloService = new VeicoloServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Veicolo v = new Veicolo(
                    request.getParameter("targa"),
                    request.getParameter("modello"),
                    request.getParameter("brand"),
                    request.getParameter("tipologia"),
                    request.getParameter("colore"),
                    null
            );

            veicoloService.insertVeicolo(v);
            request.getSession().setAttribute("veicolo", v);
            request.getServletContext().getRequestDispatcher("/methodPayment.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/assignVehicle.jsp").forward(request, response);
    }
}
