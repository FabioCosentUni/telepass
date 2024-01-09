package controller;

import exception.TelepassError;
import exception.TelepassException;
import model.Utente;
import model.Veicolo;
import model.Viaggio;
import service.VeicoloService;
import service.ViaggioService;
import service.impl.VeicoloServiceImpl;
import service.impl.ViaggioServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ViewTransponderServlet extends HttpServlet {

    private VeicoloService veicoloService;
    private ViaggioService viaggioService;

    @Override
    public void init() throws ServletException {
        super.init();

        veicoloService = new VeicoloServiceImpl();
        try {
            viaggioService = new ViaggioServiceImpl();
        } catch(TelepassException e) {
            if(TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                //TODO: non so come farlo senza req e resp
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Utente u = (Utente) req.getSession().getAttribute("utente");
            List<Veicolo> veicoli = veicoloService.getVeicoliUtente(u);
            Map<Veicolo, List<Viaggio>> viaggiMap = viaggioService.getViaggiPerVeicoli(veicoli);
            req.setAttribute("viaggiMap", viaggiMap);

            req.getServletContext().getRequestDispatcher("/viewTransponder.jsp").forward(req, resp);

        } catch(TelepassException e) {
            if(TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                req.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(req, resp);
            }
        }
    }
}
