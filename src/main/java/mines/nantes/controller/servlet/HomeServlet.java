package mines.nantes.controller.servlet;

import mines.nantes.Exception.UniciteException;
import mines.nantes.dao.ReservationDAO;
import mines.nantes.dao.RessourceDAO;
import mines.nantes.dao.TypeRessourceDAO;
import mines.nantes.dao.UtilisateurDAO;
import mines.nantes.entity.Reservation;
import mines.nantes.entity.Ressource;
import mines.nantes.entity.TypeRessource;
import mines.nantes.entity.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@javax.servlet.annotation.WebServlet(urlPatterns = {"/reservation/*"})
public class HomeServlet extends javax.servlet.http.HttpServlet {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String forward = request.getPathInfo();
        RequestDispatcher dispatcher;

        switch (forward) {
            case "/reserverRessource":
                gererReserverRessource(request);
                break;

            case "/reservationRecherche":
                gererReservationRecherche(request);
                break;

            case "/admin/reservationAdmin":
                gererReservationAdminPost(request);
                break;

            case "/admin/ressource":
                RessourceDAO ressourceDAO = new RessourceDAO();
                request.setAttribute("listRessource", ressourceDAO.getListeRessource());
                request.setAttribute("page", "admin/ressource");
                break;
            case "/admin/typeRessource":
                TypeRessourceDAO typeRessourceDAO = new TypeRessourceDAO();
                request.setAttribute("listeTypeRessource", typeRessourceDAO.getListeTypeRessource());
                request.setAttribute("page", "admin/typeRessource");
                break;
            case "/admin/utilisateur":
                UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
                request.setAttribute("listUser", utilisateurDAO.getListeUtilisateur());
                request.setAttribute("page", "admin/utilisateur");
                break;
        }

        dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String forward = request.getPathInfo();
        RequestDispatcher dispatcher;
        TypeRessourceDAO typeRessourceDAO = new TypeRessourceDAO();
        RessourceDAO ressourceDAO = new RessourceDAO();
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

        switch (forward) {
            case "/reservation":
                request.setAttribute("typeRessourceListe", typeRessourceDAO.getListeTypeRessource());
                request.setAttribute("page", "reservation");
                break;
            case "/reservationUser":
                ReservationDAO reservationDAO = new ReservationDAO();
                List<Reservation> reservations = reservationDAO.getReservationParUtilisateur((Utilisateur) request.getSession().getAttribute("user"));
                request.setAttribute("listeReservation", reservations);
                request.setAttribute("reservationPresente", reservations.size() > 0);
                request.setAttribute("page", "reservationUser");
                break;
            case "/admin/typeRessource":
                request.setAttribute("listeTypeRessource", typeRessourceDAO.getListeTypeRessource());
                request.setAttribute("page", "admin/typeRessource");
                break;
            case "/admin/ressource":
                request.setAttribute("listRessource", ressourceDAO.getListeRessource());
                request.setAttribute("page", "admin/ressource");
                break;
            case "/admin/utilisateur":
                request.setAttribute("page", "admin/utilisateur");
                request.setAttribute("listUser", utilisateurDAO.getListeUtilisateur());
                break;
            case "/admin/reservationAdmin":
                request.setAttribute("periodeRenseignee", false);
                request.setAttribute("page", "admin/reservationAdmin");
                break;
            case "/admin/ajouterRessource":
                request.setAttribute("typeRessourceListe", typeRessourceDAO.getListeTypeRessource());
                request.setAttribute("utilisateurListe", utilisateurDAO.getListeUtilisateur());
                request.setAttribute("page", "admin/ajouterRessource");
                break;
            default:
                request.setAttribute("typeRessourceListe", typeRessourceDAO.getListeTypeRessource());
                request.setAttribute("page", "reservation");
        }
        dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
        dispatcher.forward(request, response);
    }

    private void gererReserverRessource(HttpServletRequest request) {
        request.setAttribute("page", "reservation");

        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");
        String dateDebutStr = request.getParameter("dateDebutResa");
        String dateFinStr = request.getParameter("dateFinResa");
        String ressourceId = request.getParameter("ressourceSelectionnee");

        RessourceDAO ressourceDAO = new RessourceDAO();
        ReservationDAO reservationDAO = new ReservationDAO();

        try {
            if (dateDebutStr != null && dateFinStr != null && ressourceId != null) {
                Date dateDebut = sdf.parse(dateDebutStr);
                Date dateFin = sdf.parse(dateFinStr);
                Ressource ressource = ressourceDAO.trouverParId(Integer.parseInt(ressourceId));

                if(ressource != null)
                {
                    Reservation reservation = new Reservation();
                    reservation.setRessource(ressource);
                    reservation.setDateDebut(dateDebut);
                    reservation.setDateFin(dateFin);
                    reservation.setUtilisateur(utilisateur);
                    try {
                        reservationDAO.sauvegarder(reservation);
                    } catch (UniciteException e) {
                        gererErreur(request, "Impossible de réserver la ressource, veuillez réessayer");
                        return;
                    }
                }
                else
                {
                    gererErreur(request, "Impossible de réserver la ressource, veuillez réessayer");
                    return;
                }
                request.setAttribute("enregistrementOK", true);
                request.setAttribute("enregistrementMessage", "Réservation effectuée");
            } else {
                gererErreur(request, "Impossible de réserver la ressource, veuillez réessayer");
                return;
            }
        } catch (ParseException e) {
            gererErreur(request, "Impossible de réserver la ressource, veuillez réessayer");
            return;
        }
    }

    private void gererReservationRecherche(HttpServletRequest request) {
        request.setAttribute("page", "reservation");

        TypeRessourceDAO typeRessourceDAO = new TypeRessourceDAO();
        RessourceDAO ressourceDAO = new RessourceDAO();

        String typeRessourceId = request.getParameter("typeRessourceSelectionne");
        String dateDebutStr = request.getParameter("dateDebutResa");
        String dateFinStr = request.getParameter("dateFinResa");

        TypeRessource typeRessource = null;
        Date dateDebut = null;
        Date dateFin = null;

        request.setAttribute("typeRessourceSelectionne", typeRessourceId);
        request.setAttribute("dateDebutResa", dateDebutStr);
        request.setAttribute("dateFinResa", dateFinStr);

        if (!"0".equals(typeRessourceId)) {
            typeRessource = typeRessourceDAO.trouverParId(Integer.parseInt(typeRessourceId));
        } else {
            gererErreur(request, "Type de ressource sélectionné impossible à retrouver");
            return;
        }
        try {
            if (dateDebutStr != null && dateFinStr != null) {
                dateDebut = sdf.parse(dateDebutStr);
                dateFin = sdf.parse(dateFinStr);
                Calendar cal = Calendar.getInstance();
                Date dateJour = new Date(cal.getTime().getYear(),cal.getTime().getMonth(),cal.getTime().getDate());
                if(dateDebut.before(dateJour))
                {
                    gererErreur(request, "La date de début réservation doit être supérieure ou égale à la date du jour");
                    request.setAttribute("typeRessourceListe", typeRessourceDAO.getListeTypeRessource());
                    return;
                }
                if (dateFin.before(dateDebut)) {
                    gererErreur(request, "La période renseignée est non valide");
                    request.setAttribute("typeRessourceListe", typeRessourceDAO.getListeTypeRessource());
                    return;
                }
            } else {
                gererErreur(request, "La période renseignée est non valide");
                request.setAttribute("typeRessourceListe", typeRessourceDAO.getListeTypeRessource());
                return;
            }
        } catch (ParseException e) {
            gererErreur(request, "Le format des dates est non valide. Veuillez renseignez les dates avec le format <b>JJ/MM/AAA</b>");
            request.setAttribute("typeRessourceListe", typeRessourceDAO.getListeTypeRessource());
            return;
        }

        if (typeRessource != null && dateDebut != null && dateFin != null) {
            List<Ressource> listeRessource = ressourceDAO.getRessourcesAvecTypeLibres(typeRessource, dateDebut, dateFin);
            if (listeRessource.size() > 0) {
                request.setAttribute("rechercheEffectuee", true);
                request.setAttribute("ressourceDispoListe", listeRessource);
            } else {
                request.setAttribute("erreur", true);
                request.setAttribute("rechercheEffectuee", false);
                request.setAttribute("messageErreur", "Aucune ressource n'est disponible pour cette période donnée, veuillez réessayer");
            }
        } else {
            request.setAttribute("rechercheEffectuee", false);
        }
        request.setAttribute("typeRessourceListe", typeRessourceDAO.getListeTypeRessource());
    }

    private void gererReservationAdminPost(HttpServletRequest request) {
        request.setAttribute("page", "admin/reservationAdmin");

        String dateDebutStr;
        String dateFinStr;
        dateDebutStr = request.getParameter("dateDebutResa");
        dateFinStr = request.getParameter("dateFinResa");
        try {
            if (dateDebutStr != null && dateFinStr != null) {
                Date dateDebut = sdf.parse(dateDebutStr);
                Date dateFin = sdf.parse(dateFinStr);
                if (dateDebut.before(dateFin)) {
                    ReservationDAO reservationDAO = new ReservationDAO();
                    List<Reservation> listeResa = reservationDAO.getReservationParPeriode(dateDebut, dateFin);
                    if (listeResa.size() > 0) {
                        request.setAttribute("listeReservation", listeResa);
                        request.setAttribute("periodeRenseignee", true);
                    } else {
                        gererErreur(request, "Aucune réservation pour la période donnée. <b>Veuillez réessayer</b>");
                        return;
                    }
                } else {
                    gererErreur(request, "La période renseignée est non valide");
                    return;
                }
            } else {
                gererErreur(request, "La période renseignée est non valide");
                return;
            }
        } catch (ParseException e) {
            gererErreur(request, "Le format des dates est non valide. Veuillez renseignez les dates avec le format <b>JJ/MM/AAA</b>");
            return;
        }

    }

    private void gererErreur(HttpServletRequest request, String messageErreur) {
        request.setAttribute("erreur", true);
        request.setAttribute("messageErreur", messageErreur);
    }
}
