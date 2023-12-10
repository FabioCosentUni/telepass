package controller;

import model.Veicolo;
import service.VeicoloService;
import service.impl.VeicoloServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/veicolo/*")
public class VeicoloServlet extends HttpServlet {
    private VeicoloService veicoloService;

    public void init() {
        try {
            super.init();
            veicoloService = new VeicoloServiceImpl(); // Assicurati di avere un'implementazione del servizio
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        switch (action) {
            case "/":
                List<Veicolo> veicoli = veicoloService.getAllVeicoli();
                request.setAttribute("veicoli", veicoli);
                request.getRequestDispatcher("/WEB-INF/views/veicolo/list.jsp").forward(request, response);
                break;
            case "/new":
                request.getRequestDispatcher("/WEB-INF/views/veicolo/add.jsp").forward(request, response);
                break;
            case "/insert":
                //veicoloService.addVeicolo(request, response);
                response.sendRedirect(request.getContextPath() + "/veicolo/");
                break;
            case "/delete":
                //veicoloService.deleteVeicolo(request, response);
                response.sendRedirect(request.getContextPath() + "/veicolo/");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
