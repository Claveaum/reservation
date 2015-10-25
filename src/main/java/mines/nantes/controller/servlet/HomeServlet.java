package mines.nantes.controller.servlet;

import mines.nantes.Exception.UniciteException;
import mines.nantes.dao.ReservationDAO;
import mines.nantes.dao.RessourceDAO;
import mines.nantes.dao.TypeRessourceDAO;
import mines.nantes.dao.UtilisateurDAO;
import mines.nantes.entity.Reservation;
import mines.nantes.entity.Ressource;
import mines.nantes.entity.TypeRessource;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by mclaveau on 20/10/15.
 */
@javax.servlet.annotation.WebServlet(urlPatterns = {"/reservation/*"})
public class HomeServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String forward = request.getPathInfo();
        RequestDispatcher dispatcher;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        TypeRessourceDAO typeRessourceDAO = new TypeRessourceDAO();
        RessourceDAO ressourceDAO = new RessourceDAO();
        ReservationDAO reservationDAO = new ReservationDAO();
        String dateDebutStr;
        String dateFinStr;
        String ressourceId;
        Date dateDebut = null;
        Date dateFin = null;

        switch(forward) {
            case "/reserverRessource":
                request.setAttribute("page", "reservation");
                dateDebutStr = request.getParameter("dateDebutResa");
                dateFinStr = request.getParameter("dateFinResa");
                ressourceId = request.getParameter("ressourceSelectionnee");

                try {
                    if (dateDebutStr != null && dateFinStr != null) {
                        dateDebut = sdf.parse(dateDebutStr);
                        dateFin = sdf.parse(dateFinStr);
                    }
                    else
                    {
                        request.setAttribute("erreur",true);
                        request.setAttribute("messageErreur", "Impossible de réserver la ressource, veuillez réessayer");
                        break;
                    }
                } catch (ParseException e) {
                    request.setAttribute("erreur",true);
                    request.setAttribute("messageErreur", "Impossible de réserver la ressource, veuillez réessayer");
                    break;
                }
                Ressource ressource = ressourceDAO.trouverParId(Integer.parseInt(ressourceId));

                Reservation reservation = new Reservation();
                reservation.setRessource(ressource);
                reservation.setDateDebut(dateDebut);
                reservation.setDateFin(dateFin);
                try {
                    reservationDAO.sauvegarder(reservation);
                } catch (UniciteException e) {
                    request.setAttribute("erreur",true);
                    request.setAttribute("messageErreur", "Impossible de réserver la ressource, veuillez réessayer");
                    break;
                }
                request.setAttribute("enregistrementOK",true);
                request.setAttribute("enregistrementMessage", "Réservation effectuée");
                break;

            case "/reservation":
                request.setAttribute("page", "reservation");
                String typeRessourceId = request.getParameter("typeRessourceSelectionne");
                TypeRessource typeRessource = null;
                if(!"0".equals(typeRessourceId))
                {
                    typeRessource = typeRessourceDAO.trouverParId(Integer.parseInt(typeRessourceId));
                }
                dateDebutStr = request.getParameter("dateDebutResa");
                dateFinStr = request.getParameter("dateFinResa");
                try {
                    if (dateDebutStr != null && dateFinStr != null) {
                        dateDebut = sdf.parse(dateDebutStr);
                        dateFin = sdf.parse(dateFinStr);
                    }
                } catch (ParseException e) {
                    request.setAttribute("erreur",true);
                    request.setAttribute("messageErreur", "Dates non valides");
                    request.setAttribute("typeRessourceListe",typeRessourceDAO.getListeTypeRessource());
                    break;
                }

                if (typeRessource != null && dateDebut != null && dateFin != null) {
                    request.setAttribute("typeRessourceSelectionne",typeRessourceId);
                    request.setAttribute("dateDebutResa",dateDebutStr);
                    request.setAttribute("dateFinResa",dateFinStr);
                    List<Ressource> listeRessource = ressourceDAO.getRessourcesAvecTypeLibres(typeRessource, dateDebut, dateFin);
                    if(listeRessource.size()>0)
                    {
                        request.setAttribute("rechercheEffectuee", true);
                        request.setAttribute("ressourceDispoListe",listeRessource);
                    }
                    else
                    {
                        request.setAttribute("erreur",true);
                        request.setAttribute("rechercheEffectuee", false);
                        request.setAttribute("messageErreur","Aucune ressource n'est disponible pour cette période donnée, veuillez réessayer");
                    }

                } else {
                    request.setAttribute("rechercheEffectuee", false);
                }
                request.setAttribute("typeRessourceListe", typeRessourceDAO.getListeTypeRessource());
                break;

            case "/admin/reservationAdmin":
                request.setAttribute("page","admin/reservationAdmin");
                dateDebutStr = request.getParameter("dateDebutResa");
                dateFinStr = request.getParameter("dateFinResa");
                try {
                    if (dateDebutStr != null && dateFinStr != null) {
                        dateDebut = sdf.parse(dateDebutStr);
                        dateFin = sdf.parse(dateFinStr);
                    }
                } catch (ParseException e) {
                    request.setAttribute("erreur",true);
                    request.setAttribute("messageErreur", "Dates non valides");
                    break;
                }
                List<Reservation> listeResa = reservationDAO.getReservationParPeriode(dateDebut,dateFin);
                if(listeResa.size()>0)
                {
                    request.setAttribute("listeReservation", listeResa);
                    request.setAttribute("periodeRenseignee",true);
                }
                else
                {
                    request.setAttribute("erreur",true);
                    request.setAttribute("messageErreur", "Aucune réservation pour la période donnée, veuillez réessayer");
                }
                break;

            case "/admin/ressource" :
                request.setAttribute("listRessource",ressourceDAO.getListeRessource());
                request.setAttribute("page", "admin/ressource");
                break;
            case "/admin/utilisateur" :
                UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
                request.setAttribute("listUser",utilisateurDAO.getListeUtilisateur());
                request.setAttribute("page", "admin/utilisateur");
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
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

        switch(forward){
            case "/reservation":
                request.setAttribute("typeRessourceListe",typeRessourceDAO.getListeTypeRessource());
                request.setAttribute("page","reservation");
                break;
            case "/admin/typeRessource":
                request.setAttribute("listTypeRessource",typeRessourceDAO.getListeTypeRessource());
                request.setAttribute("page","admin/typeRessource");
                break;
            case "/admin/ressource":
                request.setAttribute("listRessource",ressourceDAO.getListeRessource());
                request.setAttribute("page","admin/ressource");
                break;
            case "/admin/utilisateur":
                request.setAttribute("page","admin/utilisateur");
                request.setAttribute("listUser",utilisateurDAO.getListeUtilisateur());
                break;
            case "/admin/reservationAdmin":
                request.setAttribute("periodeRenseignee",false);
                request.setAttribute("page","admin/reservationAdmin");
                break;

            case "/admin/ajouterRessource":
                request.setAttribute("typeRessourceListe",typeRessourceDAO.getListeTypeRessource());
                request.setAttribute("utilisateurListe",utilisateurDAO.getListeUtilisateur());
                request.setAttribute("page","admin/ajouterRessource");
                break;
            default:
                request.setAttribute("typeRessourceListe",typeRessourceDAO.getListeTypeRessource());
                request.setAttribute("page","reservation");
        }
        dispatcher=request.getRequestDispatcher("/WEB-INF/html/template.jsp");
        dispatcher.forward(request,response);
    }
}
