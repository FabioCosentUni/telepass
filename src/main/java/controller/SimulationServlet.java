package controller;

import dao.impl.CaselloHibernateDAOImpl;
import dao.impl.VeicoloHibernateDAOImpl;
import dao.impl.ViaggioHibernateDAOImpl;
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
            viaggioService = new ViaggioServiceImpl(new ViaggioHibernateDAOImpl(), new CaselloHibernateDAOImpl(), new VeicoloHibernateDAOImpl());
            caselloService = new CaselloServiceImpl(new CaselloHibernateDAOImpl());
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
            e.printStackTrace();

            if(TelepassError.GENERIC_ERROR.equals(e.getErrorCause())) {
                request.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
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
            autostrade = caselloService.getAllAutostrade().getAutostrade();
            request.setAttribute("caselli", caselli);
            request.setAttribute("autostrade", autostrade);
            request.getServletContext().getRequestDispatcher("/simulation.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
