package mines.nantes.controller.servlet;

import mines.nantes.Exception.UniciteException;
import mines.nantes.dao.RessourceDAO;
import mines.nantes.dao.TypeRessourceDAO;
import mines.nantes.dao.UtilisateurDAO;
import mines.nantes.entity.Ressource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mclaveau on 24/10/15.
 */
@WebServlet(urlPatterns = "/admin/ressource/*")
public class ManagerRessourceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = request.getPathInfo();
        String[] args = forward.split("/");
        switch (args[1]) {
            case "ajouter":
                gererEnregistrement(request, response, false);
                break;
            case "modifier":
                gererEnregistrement(request, response, true);
                break;
            default:
                RequestDispatcher dispatcher;
                request.setAttribute("page", "/admin/ressource");
                dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
                RessourceDAO ressourceDAO = new RessourceDAO();
                request.setAttribute("listRessource", ressourceDAO.getListeRessource());
                dispatcher.forward(request, response);
                break;
        }
    }

    private void gererEnregistrement(HttpServletRequest request, HttpServletResponse response, boolean estModification) throws ServletException, IOException {
        String idRessourceStr = request.getParameter("idRessource");
        String idTypeRessourceStr = request.getParameter("typeRessourceSelectionne");
        String nomRessource = request.getParameter("nomRessource");
        String idResponsableStr = request.getParameter("responsableSelectionne");
        String descriptionRessource = request.getParameter("descriptionRessource");
        String localisationRessource = request.getParameter("localisationRessource");

        RessourceDAO ressourceDAO = new RessourceDAO();
        TypeRessourceDAO typeRessourceDAO = new TypeRessourceDAO();
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        int idTypeRessource = 0;
        int idResponsable = 0;
        int idRessource = 0;
        try {
            idTypeRessource = Integer.parseInt(idTypeRessourceStr);
            idResponsable = Integer.parseInt(idResponsableStr);
            if (estModification) {
                idRessource = Integer.parseInt(idRessourceStr);
            }
        } catch (NumberFormatException e) {
            //
        }
        Ressource ressourceASauvegarder = new Ressource();
        if (idRessource > 0 && estModification) {
            ressourceASauvegarder = ressourceDAO.trouverParId(idRessource);
        }
        ressourceASauvegarder.setDescription(descriptionRessource);
        ressourceASauvegarder.setNom(nomRessource);
        ressourceASauvegarder.setLocalisation(localisationRessource);
        ressourceASauvegarder.setType(typeRessourceDAO.trouverParId(idTypeRessource));
        ressourceASauvegarder.setResponsable(utilisateurDAO.trouverParId(idResponsable));
        try {
            ressourceDAO.sauvegarder(ressourceASauvegarder);
            RequestDispatcher dispatcher;
            request.setAttribute("enregistrementOK", true);
            if (estModification) {
                request.setAttribute("enregistrementMessage", "Modification effectuée");
            } else {
                request.setAttribute("enregistrementMessage", "Ajout de ressource effectué");
            }
            dispatcher = request.getRequestDispatcher("/reservation/admin/ressource");
            dispatcher.forward(request, response);
        } catch (UniciteException e) {

            request.setAttribute("erreur", true);

            request.setAttribute("messageErreur", e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String forward = request.getPathInfo();
        String[] args = forward.split("/");
        switch (args[1]) {
            case "ajouter":
                gererAjouterGet(request, response);
                break;
            case "modifier":
                gererModificationGet(request, response, args);
                break;
            case "supprimer":
                gererSupprimerGet(request, response, args);
                break;
            default:
                RequestDispatcher dispatcher;
                request.setAttribute("page", "/admin/ressource");
                dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
                RessourceDAO ressourceDAO = new RessourceDAO();
                request.setAttribute("listRessource", ressourceDAO.getListeRessource());
                dispatcher.forward(request, response);
                break;
        }
    }


    private void gererAjouterGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        request.setAttribute("page", "admin/ajoutModifRessource");
        dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");

        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        TypeRessourceDAO typeRessourceDAO = new TypeRessourceDAO();
        request.setAttribute("typeRessourceListe", typeRessourceDAO.getListeTypeRessource());
        request.setAttribute("utilisateurListe", utilisateurDAO.getListeUtilisateur());

        dispatcher.forward(request, response);
    }

    private void gererModificationGet(HttpServletRequest request, HttpServletResponse response, String[] args) throws ServletException, IOException {
        int idRessource = Integer.parseInt(args[2]);
        Ressource ressourceAModifier = null;
        RessourceDAO ressourceDAO = new RessourceDAO();
        TypeRessourceDAO typeRessourceDAO = new TypeRessourceDAO();
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        if (idRessource > 0) {
            ressourceAModifier = ressourceDAO.trouverParId(idRessource);
            request.setAttribute("ressource", ressourceAModifier);
            request.setAttribute("modifier", true);
            request.setAttribute("typeRessourceListe", typeRessourceDAO.getListeTypeRessource());
            request.setAttribute("utilisateurListe", utilisateurDAO.getListeUtilisateur());
            request.setAttribute("page", "admin/ajoutModifRessource");
        }
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
        dispatcher.forward(request, response);
    }

    private void gererSupprimerGet(HttpServletRequest request, HttpServletResponse response, String[] args) throws ServletException, IOException {
        String idRessourceStr = args[2];
        RessourceDAO ressourceDAO = new RessourceDAO();
        int idRessource = 0;
        try {
            idRessource = Integer.parseInt(idRessourceStr);
        } catch (NumberFormatException e) {
            // Impossible de récupérer la ressource à supprimer
        }
        if (idRessource > 0) {
            Ressource ressourceASupprimer = ressourceDAO.trouverParId(idRessource);
            ressourceDAO.supprimer(ressourceASupprimer);
        }

        RequestDispatcher dispatcher;
        request.setAttribute("page", "admin/ressource");
        request.setAttribute("enregistrementOK", true);
        request.setAttribute("enregistrementMessage", "Suppression effectuée");
        request.setAttribute("listRessource", ressourceDAO.getListeRessource());
        dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
        dispatcher.forward(request, response);
    }
}
