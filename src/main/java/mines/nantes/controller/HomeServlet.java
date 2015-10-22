package mines.nantes.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

/**
 * Created by mclaveau on 20/10/15.
 */
@javax.servlet.annotation.WebServlet(urlPatterns = {"/reservation/*"})
public class HomeServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String forward = request.getPathInfo();
        RequestDispatcher dispatcher;

        switch(forward){
            case "/reservation":
                request.setAttribute("page","reservation");
                break;
            case "/typeRessource":
                request.setAttribute("page","typeRessource");
                break;
            case "/ressource":
                request.setAttribute("page","ressource");
                break;
            case "/utilisateur":
                request.setAttribute("page","utilisateur");
                break;
            case "/reservationAdmin":
                request.setAttribute("page","reservationAdmin");
                break;
            default:
                request.setAttribute("page","reservation");
        }
        dispatcher=request.getRequestDispatcher("/html/template.jsp");
        dispatcher.forward(request,response);
    }
}
