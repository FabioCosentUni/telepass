package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet per la gestione del logout degli utenti.
 */
public class LogoutServlet extends HttpServlet {

    /**
     * Inizializza la servlet.
     *
     * @throws ServletException se si verifica un errore durante l'inizializzazione.
     */
    public void init() {
        try{
            super.init();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gestisce le richieste GET per il logout degli utenti.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException se si verifica un errore durante la gestione della richiesta.
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //invalido la sessione e reindirizzo alla pagina di index
        request.getSession().invalidate();
        request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
