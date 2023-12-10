package controller;

import model.Casello;
import service.CaselloService;
import service.impl.CaselloServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/casello/*")
public class CaselloServlet extends HttpServlet {
    private CaselloService caselloService;

    public void init() {
        try {
            super.init();
            caselloService = new CaselloServiceImpl(); // Assicurati di avere un'implementazione del servizio
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
                // Mostra la lista dei caselli
                List<Casello> caselli = caselloService.getAllCaselli();
                // Includi i caselli nella richiesta per visualizzarli nella JSP
                request.setAttribute("caselli", caselli);
                request.getRequestDispatcher("/WEB-INF/views/casello/list.jsp").forward(request, response);
                break;
            case "/new":
                // Mostra il form per l'aggiunta di un nuovo casello
                request.getRequestDispatcher("/WEB-INF/views/casello/add.jsp").forward(request, response);
                break;
            case "/insert":
                // Esegui l'inserimento di un nuovo casello
                //caselloService.addCasello(request, response);
                // Redirect alla lista dei caselli dopo l'inserimento
                response.sendRedirect(request.getContextPath() + "/casello/");
                break;
            case "/delete":
                // Esegui l'eliminazione di un casello
                //caselloService.deleteCasello(request, response);
                // Redirect alla lista dei caselli dopo l'eliminazione
                response.sendRedirect(request.getContextPath() + "/casello/");
                break;
            // Aggiungi altri casi a seconda delle tue esigenze
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
