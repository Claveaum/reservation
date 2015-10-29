package mines.nantes.controller.servlet;

import mines.nantes.Exception.UniciteException;
import mines.nantes.dao.RessourceDAO;
import mines.nantes.dao.UtilisateurDAO;
import mines.nantes.entity.Utilisateur;

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
@WebServlet(urlPatterns = "/admin/utilisateur/*")
public class ManagerUtilisateurServlet extends HttpServlet {
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
                request.setAttribute("page", "/admin/utilisateur");
                dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
                UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
                request.setAttribute("listUser", utilisateurDAO.getListeUtilisateur());
                dispatcher.forward(request, response);
                break;
        }
    }

    private void gererEnregistrement(HttpServletRequest request, HttpServletResponse response, boolean estModification) throws ServletException, IOException {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

        RequestDispatcher dispatcher;

        String idUtilisateurStr = request.getParameter("idUtilisateur");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String admin = request.getParameter("admin");

        Utilisateur utilisateurASauvegarder = new Utilisateur();
        int idUtilisateur = 0;
        try {
            if (estModification) {
                idUtilisateur = Integer.parseInt(idUtilisateurStr);

                if (idUtilisateur > 0 && estModification) {
                    utilisateurASauvegarder = utilisateurDAO.trouverParId(idUtilisateur);
                    if (utilisateurASauvegarder == null) {
                        gererErreur(request, "Impossible de modifier l'utilisateur");
                        dispatcher = request.getRequestDispatcher("/reservation/admin/utilisateur");
                        dispatcher.forward(request, response);
                        return;
                    }
                }
            }
        } catch (NumberFormatException e) {
            gererErreur(request, "Impossible de récupérer l'utilisateur");
        }

        utilisateurASauvegarder.setLogin(login);
        utilisateurASauvegarder.setPassword(password);
        utilisateurASauvegarder.setNom(nom);
        utilisateurASauvegarder.setPrenom(prenom);
        utilisateurASauvegarder.setEmail(email);
        utilisateurASauvegarder.setTelephone(telephone);
        utilisateurASauvegarder.setAdmin(Boolean.valueOf(admin));
        try {
            utilisateurDAO.sauvegarder(utilisateurASauvegarder);
            if (estModification) {
                request.setAttribute("enregistrementMessage", "Modification effectuée");
            } else {
                request.setAttribute("enregistrementMessage", "Ajout d'utilisateur effectué");
            }
            request.setAttribute("enregistrementOK", true);


        } catch (UniciteException e) {
            gererErreur(request, e.getMessage());
        }
        dispatcher = request.getRequestDispatcher("/reservation/admin/utilisateur");
        dispatcher.forward(request, response);
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
                request.setAttribute("page", "admin/utilisateur");
                dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
                UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
                request.setAttribute("listUser", utilisateurDAO.getListeUtilisateur());
                dispatcher.forward(request, response);
                break;
        }
    }


    private void gererAjouterGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        request.setAttribute("page", "admin/ajoutModifUtilisateur");
        dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
        dispatcher.forward(request, response);
    }

    private void gererModificationGet(HttpServletRequest request, HttpServletResponse response, String[] args) throws ServletException, IOException {
        int idUtilisateur = Integer.parseInt(args[2]);
        Utilisateur utilisateurAModifier = null;
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        if (idUtilisateur > 0) {
            utilisateurAModifier = utilisateurDAO.trouverParId(idUtilisateur);
            if (utilisateurAModifier != null) {
                request.setAttribute("utilisateur", utilisateurAModifier);
                request.setAttribute("modifier", true);
                request.setAttribute("page", "admin/ajoutModifUtilisateur");
            } else {

                gererErreur(request, "Impossible de trouver l'utilisateur à modifier");
                request.setAttribute("listUser", utilisateurDAO.getListeUtilisateur());
            }
        }
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
        dispatcher.forward(request, response);
    }

    private void gererSupprimerGet(HttpServletRequest request, HttpServletResponse response, String[] args) throws ServletException, IOException {
        String idUtilisateurStr = args[2];
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        int idUtilisateur = 0;
        RequestDispatcher dispatcher;
        try {
            idUtilisateur = Integer.parseInt(idUtilisateurStr);
        } catch (NumberFormatException e) {
            gererErreur(request, "Impossible de supprimer l'utilisateur");
            request.setAttribute("listUser", utilisateurDAO.getListeUtilisateur());
            dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
            dispatcher.forward(request, response);
            return;
        }
        if (idUtilisateur > 0) {
            Utilisateur utilisateurASupprimer = utilisateurDAO.trouverParId(idUtilisateur);
            if (utilisateurASupprimer == null) {
                request.setAttribute("page", "admin/utilisateur");
                gererErreur(request, "Suppression impossible, l'utilisateur n'existe pas");
            } else {
                RessourceDAO ressourceDAO = new RessourceDAO();
                if (ressourceDAO.getRessourcesResponsable(utilisateurASupprimer.getId()).size() > 0) {
                    request.setAttribute("page", "admin/utilisateur");
                    gererErreur(request, "Impossible de supprimer l'utilisateur car il est responsable d'une ou plusieurs ressource(s)");
                } else {
                    utilisateurDAO.supprimer(utilisateurASupprimer);
                    request.setAttribute("page", "admin/utilisateur");
                    request.setAttribute("enregistrementOK", true);
                    request.setAttribute("enregistrementMessage", "Suppression effectuée");
                }
            }
            request.setAttribute("listUser", utilisateurDAO.getListeUtilisateur());
            dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void gererErreur(HttpServletRequest request, String messageErreur) {
        request.setAttribute("erreur", true);
        request.setAttribute("messageErreur", messageErreur);
    }

}
