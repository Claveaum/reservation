package mines.nantes.controller.servlet;

import mines.nantes.dao.ReservationDAO;
import mines.nantes.entity.Reservation;
import mines.nantes.entity.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pierre on 26/10/2015.
 */
@WebServlet(urlPatterns = "/annulerReservation/*")
public class ManagerAnnulerReservationServlet extends javax.servlet.http.HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = request.getPathInfo();
        String[] args = forward.split("/");

        Utilisateur utilisateurCourant = (Utilisateur) request.getSession().getAttribute("user");
        String idReservationStr = args[1];

        ReservationDAO reservationDAO = new ReservationDAO();
        boolean erreurPresente = false;

        try {
            int idReservation = Integer.parseInt(idReservationStr);
            if (idReservation > 0) {
                Reservation reservation = reservationDAO.trouverParId(idReservation);
                if (reservation != null) {
                    if (reservation.getUtilisateur().getId() == utilisateurCourant.getId() || utilisateurCourant.isAdmin()) {
                        reservationDAO.supprimer(reservation);
                    } else {
                        gererErreur(request, "Vous n'êtes pas autorisé à annuler cette réservation");
                        erreurPresente = true;
                    }
                } else {
                    gererErreur(request, "Réservation à supprimer inexistante");
                    erreurPresente = true;
                }
            }
        } catch (NumberFormatException e) {
            gererErreur(request, "Réservation à supprimer inexistante");
            erreurPresente = true;
        }
        RequestDispatcher dispatcher;
        if (!erreurPresente) {
            request.setAttribute("enregistrementOK", true);
            request.setAttribute("enregistrementMessage", "Réservation annulée");
        }
        if (utilisateurCourant.isAdmin()) {
            request.setAttribute("page", "admin/reservationAdmin");
            request.setAttribute("listeReservation", reservationDAO.getListeReservation());
        } else {
            request.setAttribute("page", "reservationUser");
            List<Reservation> reservations = reservationDAO.getReservationParUtilisateur(utilisateurCourant);
            request.setAttribute("listeReservation", reservations);
            request.setAttribute("reservationPresente", reservations.size() > 0);
        }
        dispatcher = request.getRequestDispatcher("/WEB-INF/html/template.jsp");
        dispatcher.forward(request, response);
    }


    private void gererErreur(HttpServletRequest request, String messageErreur) {
        request.setAttribute("erreur", true);
        request.setAttribute("messageErreur", messageErreur);

    }
}
