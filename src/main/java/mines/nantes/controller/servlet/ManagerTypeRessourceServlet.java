package mines.nantes.controller.servlet;

import mines.nantes.Exception.UniciteException;
import mines.nantes.dao.RessourceDAO;
import mines.nantes.dao.TypeRessourceDAO;
import mines.nantes.entity.TypeRessource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pierre on 25/10/2015.
 */
@WebServlet(urlPatterns = "/admin/typeRessource/*")
public class ManagerTypeRessourceServlet extends HttpServlet {
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
                request.setAttribute("page", "/admin/typeRessource");
                dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
                TypeRessourceDAO typeRessourceDAO = new TypeRessourceDAO();
                request.setAttribute("listeTypeRessource", typeRessourceDAO.getListeTypeRessource());
                dispatcher.forward(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String forward = request.getPathInfo();
        String[] args = forward.split("/");

        RequestDispatcher dispatcher;
        TypeRessourceDAO typeRessourceDAO = new TypeRessourceDAO();
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
            case "supprimerValider":
                String idTypeRessourceStr = args[2];
                TypeRessource typeRessourceASupprimer = new TypeRessource();
                try {
                    int idTypeRessource = Integer.parseInt(idTypeRessourceStr);
                    typeRessourceASupprimer = typeRessourceDAO.trouverParId(idTypeRessource);

                } catch (NumberFormatException e) {
                    // Impossible de récupérer le type de ressource
                }
                supprimerTypeRessource(request, typeRessourceASupprimer);
                dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
                dispatcher.forward(request, response);
                break;
            default:
                request.setAttribute("page", "/admin/typeRessource");
                dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
                request.setAttribute("listeTypeRessource", typeRessourceDAO.getListeTypeRessource());
                dispatcher.forward(request, response);
                break;
        }
    }


    private void gererAjouterGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        request.setAttribute("page", "admin/ajoutModifTypeRessource");
        dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
        dispatcher.forward(request, response);
    }

    private void gererModificationGet(HttpServletRequest request, HttpServletResponse response, String[] args) throws ServletException, IOException {
        int idTypeRessource = Integer.parseInt(args[2]);

        TypeRessourceDAO typeRessourceDAO = new TypeRessourceDAO();
        TypeRessource typeRessourceAModifier = null;
        if (idTypeRessource > 0) {
            typeRessourceAModifier = typeRessourceDAO.trouverParId(idTypeRessource);
            request.setAttribute("typeRessource", typeRessourceAModifier);
            request.setAttribute("modifier", true);
            request.setAttribute("page", "admin/ajoutModifTypeRessource");
        }
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
        dispatcher.forward(request, response);
    }

    private void gererSupprimerGet(HttpServletRequest request, HttpServletResponse response, String[] args) throws ServletException, IOException {
        String idTypeRessourceStr = args[2];
        TypeRessourceDAO typeRessourceDAO = new TypeRessourceDAO();
        int idTypeRessource = 0;
        try {
            idTypeRessource = Integer.parseInt(idTypeRessourceStr);
        } catch (NumberFormatException e) {
            // Impossible de récupérer la ressource à supprimer
        }
        if (idTypeRessource > 0) {
            TypeRessource typeRessourceASupprimer = typeRessourceDAO.trouverParId(idTypeRessource);
            RessourceDAO ressourceDAO = new RessourceDAO();
            RequestDispatcher dispatcher;
            if (ressourceDAO.getRessourcesAvecType(idTypeRessource).size() > 0) {
                request.setAttribute("page", "admin/typeRessource");
                request.setAttribute("alerte", true);
                request.setAttribute("messageAlerte", "Attention, si vous supprimez ce type de ressource, toutes les ressources associées seront supprimées");
                request.setAttribute("typeRessource", typeRessourceASupprimer);
            } else {
                supprimerTypeRessource(request, typeRessourceASupprimer);
            }
            dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void supprimerTypeRessource(HttpServletRequest request, TypeRessource typeRessourceASupprimer) {
        TypeRessourceDAO typeRessourceDAO = new TypeRessourceDAO();
        typeRessourceDAO.supprimer(typeRessourceASupprimer);
        request.setAttribute("page", "admin/typeRessource");
        request.setAttribute("enregistrementOK", true);
        request.setAttribute("enregistrementMessage", "Suppression effectuée");
        request.setAttribute("listeTypeRessource", typeRessourceDAO.getListeTypeRessource());
    }


    private void gererEnregistrement(HttpServletRequest request, HttpServletResponse response, boolean estModification) throws ServletException, IOException {
        String idTypeRessourceStr = request.getParameter("idTypeRessource");
        String nom = request.getParameter("nom");

        TypeRessourceDAO typeRessourceDAO = new TypeRessourceDAO();
        int idTypeRessource = 0;
        try {
            if (estModification) {
                idTypeRessource = Integer.parseInt(idTypeRessourceStr);
            }
        } catch (NumberFormatException e) {
            //
        }
        TypeRessource typeRessourceASauvegarder = new TypeRessource();
        if (idTypeRessource > 0 && estModification) {
            typeRessourceASauvegarder = typeRessourceDAO.trouverParId(idTypeRessource);
        }
        typeRessourceASauvegarder.setNom(nom);

        RequestDispatcher dispatcher;
        try {
            typeRessourceDAO.sauvegarder(typeRessourceASauvegarder);
            request.setAttribute("enregistrementOK", true);
            if (estModification) {
                request.setAttribute("enregistrementMessage", "Modification effectuée");
            } else {
                request.setAttribute("enregistrementMessage", "Ajout de ressource effectué");
            }
        } catch (UniciteException e) {
            request.setAttribute("erreur", true);
            request.setAttribute("messageErreur", e.getMessage());
        }
        dispatcher = request.getRequestDispatcher("/reservation/admin/typeRessource");
        dispatcher.forward(request, response);
    }
}
