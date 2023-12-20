package controller;

import dao.CaselloDAO;
import dao.impl.CaselloDAOImpl;
import service.ViaggioService;
import service.impl.ViaggioServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimulationServlet extends HttpServlet {

    private ViaggioService viaggioService;
    private CaselloDAO caselloDAO;

    public void init() {
        try{
            super.init();
            viaggioService = new ViaggioServiceImpl();
            caselloDAO = new CaselloDAOImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String cf = request.getParameter("codice_fiscale");
        String password = request.getParameter("password");
        Utente u;
        try {
            u = utenteService.login(cf, password);
            request.getSession().setAttribute("utente", u);
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (UserException e) {
            request.setAttribute("error", e.getErrorCause());
            request.setAttribute("codice_fiscale", request.getParameter("codice_fiscale"));
            request.setAttribute("password", request.getParameter("password"));
            request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, e.getMessage());
        }*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*try {
            List<Casello> caselli = new ArrayList<Casello>();
            List<String> autostrade = new ArrayList<String>();
            caselli= caselloDAO.getAllCaselli();
            autostrade = caselloDAO.getAllAutostrade();
            autostrade.add("uno");
            autostrade.add("due");
            autostrade.add("tre");
            request.setAttribute("caselli", caselli);
            request.setAttribute("autostrade", autostrade);
            request.getServletContext().getRequestDispatcher("/simulation.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
}
