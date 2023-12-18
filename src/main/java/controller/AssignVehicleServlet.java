package controller;

import exception.user.VehicleError;
import exception.user.VehicleException;
import model.Utente;
import model.Veicolo;
import service.VeicoloService;
import service.impl.VeicoloServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AssignVehicleServlet extends HttpServlet{

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
                    request.getParameter("targa_veicolo"),
                    request.getParameter("modello_veicolo"),
                    request.getParameter("brand"),
                    request.getParameter("tipologia"),
                    request.getParameter("colore"),
                    null
            );
            Utente u = (Utente) request.getSession().getAttribute("utente");

            v = veicoloService.insertVeicolo(v, u);
            if(v == null) {
                throw new VehicleException(VehicleError.GENERIC_ERROR);
            } else {
                request.getSession().setAttribute("veicolo", v);
                request.getServletContext().getRequestDispatcher("/methodPayment.jsp").forward(request, response);
            }
        } catch (VehicleException e){
            e.printStackTrace();
            request.setAttribute("error", e.getErrorCause());
            request.setAttribute("targa", request.getParameter("targa_veicolo"));
            request.setAttribute("modello", request.getParameter("modello_veicolo"));
            request.setAttribute("brand", request.getParameter("brand"));
            request.setAttribute("tipologia", request.getParameter("tipologia"));
            request.setAttribute("colore", request.getParameter("colore"));
            request.getServletContext().getRequestDispatcher("/assignVehicle.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/assignVehicle.jsp").forward(request, response);
    }
}
