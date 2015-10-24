package mines.nantes.controller.servlet;

import mines.nantes.dao.RessourceDAO;
import mines.nantes.dao.TypeRessourceDAO;
import mines.nantes.dao.UtilisateurDAO;
import mines.nantes.entity.Utilisateur;

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
                TypeRessourceDAO typeRessourceDAO = new TypeRessourceDAO();
                request.setAttribute("listTypeRessource",typeRessourceDAO.getListeTypeRessource());
                request.setAttribute("page","typeRessource");
                break;
            case "/ressource":
                RessourceDAO ressourceDAO = new RessourceDAO();
                request.setAttribute("listRessource",ressourceDAO.getListeRessource());
                request.setAttribute("page","ressource");
                break;
            case "/utilisateur":
                UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
                request.setAttribute("page","utilisateur");
                request.setAttribute("listUser",utilisateurDAO.getListeUtilisateur());
                break;
            case "/reservationAdmin":
                request.setAttribute("page","reservationAdmin");
                break;
            default:
                request.setAttribute("page","reservation");
        }
        dispatcher=request.getRequestDispatcher("/WEB-INF/html/template.jsp");
        dispatcher.forward(request,response);
    }
}
