package mines.nantes.dao;

import mines.nantes.entity.Reservation;
import mines.nantes.entity.Ressource;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by Pierre on 21/10/2015.
 */
public class ReservationDAO extends AbstractDAO<Reservation> {

    public ReservationDAO() {
        super(Reservation.class);
    }

    /**
     * Retourne la liste des réservations d'une ressource à partir d'une date
     *
     * @param ressource
     * @param dateDebut
     * @return
     */
    public List<Reservation> getReservationParRessource(Ressource ressource, Date dateDebut) {
        Query q = Manager.getEntityManager().createQuery(
                "SELECT r FROM " + Reservation.class.getName() +
                        " r WHERE r.ressource = :ressource AND dateDebut >= :dateDebut");
        q.setParameter("dateDebut", dateDebut);
        q.setParameter("ressource", ressource);
        return (List) q.getResultList();
    }

    /**
     * Retourne la totalité des réservations
     *
     * @return
     */
    public List<Reservation> getListeReservation() {
        Query q = Manager.getEntityManager().createQuery(
                "SELECT r FROM " + Reservation.class.getName() + " r");
        return (List) q.getResultList();
    }

    /**
     * Retourne la liste des réservations pour une période donnée
     *
     * @param dateDebut
     * @param dateFin
     * @return
     */
    public List<Reservation> getReservationParPeriode(Date dateDebut, Date dateFin) {
        Query q = Manager.getEntityManager().createQuery(
                "SELECT r FROM " + Reservation.class.getName() + " r WHERE dateDebut >= :dateDebut AND dateFin <= :dateFin");
        q.setParameter("dateDebut", dateDebut);
        q.setParameter("dateFin", dateFin);
        return (List) q.getResultList();
    }
}
