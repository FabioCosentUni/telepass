package controller;

import com.google.gson.Gson;
import exception.TelepassError;
import exception.TelepassException;
import model.Transponder;
import model.Utente;
import model.dto.DeleteTransponderResponseDTO;
import service.TransponderService;
import service.impl.TransponderServiceImpl;
import utils.PermissionFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RevokeTransponderServlet extends HttpServlet {

    private TransponderService transponderService;

    @Override
    public void init() throws ServletException {
        this.transponderService = new TransponderServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente u = (Utente) req.getSession().getAttribute("utente");

        if(!PermissionFilter.checkAdmin(u)) {
            req.setAttribute("error", TelepassError.VIEW_NOT_PERMITTED);
            req.getServletContext().getRequestDispatcher("/revokeTransponder.jsp").forward(req, resp);
            return;
        }

        List<Transponder> transponderList = transponderService.getTrasponders();

        req.setAttribute("transponders", transponderList);

        req.getServletContext().getRequestDispatcher("/revokeTransponder.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();

        try {
            transponderService.deleteTransponderById(Long.parseLong(req.getParameter("transponder_id")));

            DeleteTransponderResponseDTO dto = new DeleteTransponderResponseDTO();
            dto.setTransponderId(req.getParameter("transponder_id"));

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(dto));
        } catch (TelepassException e) {
            throw new RuntimeException(e);
        }

    }
}
