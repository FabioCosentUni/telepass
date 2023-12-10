package controller;

import model.Viaggio;
import service.ViaggioService;
import service.impl.ViaggioServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/viaggio/*")
public class ViaggioServlet extends HttpServlet {
    private ViaggioService viaggioService;

    public void init() {
        try {
            super.init();
            viaggioService = new ViaggioServiceImpl(); // Assicurati di avere un'implementazione del servizio
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
                List<Viaggio> viaggi = viaggioService.getAllViaggi();
                request.setAttribute("viaggi", viaggi);
                request.getRequestDispatcher("/WEB-INF/views/viaggio/list.jsp").forward(request, response);
                break;
            case "/new":
                request.getRequestDispatcher("/WEB-INF/views/viaggio/add.jsp").forward(request, response);
                break;
            case "/insert":
                //viaggioService.addViaggio(request, response);
                response.sendRedirect(request.getContextPath() + "/viaggio/");
                break;
            case "/delete":
                //viaggioService.deleteViaggio(request, response);
                response.sendRedirect(request.getContextPath() + "/viaggio/");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
