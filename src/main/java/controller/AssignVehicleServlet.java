package controller;

import exception.TelepassError;
import exception.TelepassException;
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
                    request.getParameter("tipologia_veicolo"),
                    null
            );

            veicoloService.validateVeicolo(v);

            request.getSession().setAttribute("veicolo", v);
            request.getServletContext().getRequestDispatcher("/methodPayment.jsp").forward(request, response);
        } catch (TelepassException e){

            if(TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                //TODO handle default page error
                return;
            }

            request.setAttribute("error", e.getErrorCause());
            request.setAttribute("targa_veicolo", request.getParameter("targa_veicolo"));
            request.setAttribute("tipologia_veicolo", request.getParameter("tipologia"));
            request.getServletContext().getRequestDispatcher("/assignVehicle.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/assignVehicle.jsp").forward(request, response);
    }
}
