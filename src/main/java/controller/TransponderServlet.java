package controller;

import model.Transponder;
import service.TransponderService;
import service.impl.TransponderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/transponder/*")
public class TransponderServlet extends HttpServlet {
    private TransponderService transponderService;

    public void init() {
        try {
            super.init();
            transponderService = new TransponderServiceImpl(); // Assicurati di avere un'implementazione del servizio
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
                List<Transponder> transponders = transponderService.getAllTransponders();
                request.setAttribute("transponders", transponders);
                request.getRequestDispatcher("/WEB-INF/views/transponder/list.jsp").forward(request, response);
                break;
            case "/new":
                request.getRequestDispatcher("/WEB-INF/views/transponder/add.jsp").forward(request, response);
                break;
            case "/insert":
                //transponderService.addTransponder(request, response);
                response.sendRedirect(request.getContextPath() + "/transponder/");
                break;
            case "/delete":
                //transponderService.deleteTransponder(request, response);
                response.sendRedirect(request.getContextPath() + "/transponder/");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
