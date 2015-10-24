package mines.nantes.controller.servlet;

import mines.nantes.dao.RessourceDAO;
import mines.nantes.dao.TypeRessourceDAO;
import mines.nantes.dao.UtilisateurDAO;
import mines.nantes.entity.Ressource;
import mines.nantes.entity.TypeRessource;
import mines.nantes.entity.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mclaveau on 20/10/15.
 */
@javax.servlet.annotation.WebServlet(urlPatterns = {"/reservation/*"})
public class HomeServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String forward = request.getPathInfo();
        RequestDispatcher dispatcher;
        TypeRessourceDAO typeRessourceDAO = new TypeRessourceDAO();
        RessourceDAO ressourceDAO = new RessourceDAO();

        switch(forward) {
            case "/reservation":
                String typeRessourceId = request.getParameter("typeRessourceSelectionne");
                TypeRessource typeRessource = null;
                if(!"0".equals(typeRessourceId))
                {
                    typeRessource = typeRessourceDAO.trouverParId(Integer.parseInt(typeRessourceId));
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateDebutStr = request.getParameter("dateDebutResa");
                String dateFinStr = request.getParameter("dateFinResa");
                Date dateDebut = null;
                Date dateFin = null;
                try {
                    if (dateDebutStr != null && dateFinStr != null) {
                        dateDebut = sdf.parse(dateDebutStr);
                        dateFin = sdf.parse(dateFinStr);
                    }
                } catch (ParseException e) {

                }

                if (typeRessource != null && dateDebut != null && dateFin != null) {
                    request.setAttribute("typeRessourceSelectionne",typeRessourceId);
                    request.setAttribute("dateDebutResa",dateDebutStr);
                    request.setAttribute("dateFinResa",dateFinStr);
                    request.setAttribute("rechercheEffectuee", true);
                    request.setAttribute("ressourceDispoListe",
                            ressourceDAO.getRessourcesAvecTypeLibres(typeRessource, dateDebut, dateFin));
                } else {
                    request.setAttribute("rechercheEffectuee", false);
                }
                request.setAttribute("typeRessourceListe", typeRessourceDAO.getListeTypeRessource());
                request.setAttribute("page", "reservation");
                break;
        }
        dispatcher=request.getRequestDispatcher("/WEB-INF/html/template.jsp");
        dispatcher.forward(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String forward = request.getPathInfo();
        RequestDispatcher dispatcher;
        TypeRessourceDAO typeRessourceDAO = new TypeRessourceDAO();
        RessourceDAO ressourceDAO = new RessourceDAO();

        switch(forward){
            case "/reservation":
                TypeRessource typeRessource = (TypeRessource) request.getAttribute("typeRessourceSelectionne");

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateDebutStr = (String) request.getAttribute("dateDebutResa");
                String dateFinStr = (String) request.getAttribute("dateFinResa");
                Date dateDebut = null;
                Date dateFin = null;
                try {
                    if(dateDebutStr!=null && dateFinStr!=null)
                    {
                        dateDebut = sdf.parse(dateDebutStr);
                        dateFin = sdf.parse(dateFinStr);
                    }
                } catch (ParseException e) {

                }

                if(typeRessource!=null && typeRessource.getId()!= 0 && dateDebut != null && dateFin != null)
                {
                    request.setAttribute("rechercheEffectuee",true);
                    request.setAttribute("ressourceDispoListe",
                            ressourceDAO.getRessourcesAvecTypeLibres(typeRessource,dateDebut,dateFin));
                }
                else
                {
                    request.setAttribute("rechercheEffectuee",false);
                }
                request.setAttribute("typeRessourceListe",typeRessourceDAO.getListeTypeRessource());
                request.setAttribute("page","reservation");
                break;
            case "/typeRessource":
                request.setAttribute("listTypeRessource",typeRessourceDAO.getListeTypeRessource());
                request.setAttribute("page","typeRessource");
                break;
            case "/ressource":
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
                request.setAttribute("typeRessourceListe",typeRessourceDAO.getListeTypeRessource());
                request.setAttribute("page","reservation");
        }
        dispatcher=request.getRequestDispatcher("/WEB-INF/html/template.jsp");
        dispatcher.forward(request,response);
    }
}
