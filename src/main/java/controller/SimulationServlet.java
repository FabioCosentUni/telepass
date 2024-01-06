package controller;

import exception.TelepassError;
import exception.TelepassException;
import model.Casello;
import service.CaselloService;
import service.ViaggioService;
import service.impl.CaselloServiceImpl;
import service.impl.ViaggioServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SimulationServlet extends HttpServlet {

    private ViaggioService viaggioService;
    private CaselloService caselloService;

    public void init() {
        try {
            super.init();
            viaggioService = new ViaggioServiceImpl();
            caselloService = new CaselloServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targaVeicolo = request.getParameter("veicoloSel");
        Long idCaselloPartenza = Long.valueOf(request.getParameter("entrataSelect"));
        Long idCaselloArrivo = Long.valueOf(request.getParameter("uscitaSelect"));
        try {
            viaggioService.insertViaggio(idCaselloPartenza, idCaselloArrivo, targaVeicolo);
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

        } catch (TelepassException e) {
            /*request.setAttribute("error", e.getMessage());
            doGet(request, response);

             */
            if(TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                //TODO --> redirect ad una pagina di errore generico
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Casello> caselli;
            List<String> autostrade;
            String autostrada = request.getParameter("autostrada");

            caselli = caselloService.getAllCaselli();
            if (autostrada != null && !autostrada.equals("Autostrada")) {
                request.setAttribute("autostradaSel", autostrada);
            }
            autostrade = caselloService.getAllAutostrade();
            request.setAttribute("caselli", caselli);
            request.setAttribute("autostrade", autostrade);
            request.getServletContext().getRequestDispatcher("/simulation.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
