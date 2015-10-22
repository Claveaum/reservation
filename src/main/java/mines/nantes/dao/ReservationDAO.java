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
     * Retourne la liste des r�servations d'une ressource � partir d'une date
     * @param ressource
     * @param dateDebut
     * @return
     */
    public List<Reservation> getReservationParRessource(Ressource ressource, Date dateDebut)
    {
        Query q = Manager.getEntityManager().createQuery(
                "SELECT r FROM " + Reservation.class.getName() +
                        " r WHERE r.ressource = :ressource AND dateDebut >= :dateDebut");
        q.setParameter("dateDebut", dateDebut);
        q.setParameter("ressource", ressource);
        return (List) q.getResultList();
    }

    /**
     * Retourne la totalit� des r�servations
     * @return
     */
    public List<Reservation> getListeReservation()
    {
        Query q = Manager.getEntityManager().createQuery(
                "SELECT r FROM " + Reservation.class.getName() + " r");
        return (List) q.getResultList();
    }

    /**
     * Retourne la liste des r�servations pour une p�riode donn�e
     * @param dateDebut
     * @param dateFin
     * @return
     */
    public List<Reservation> getReservationParPeriode(Date dateDebut, Date dateFin)
    {
        Query q = Manager.getEntityManager().createQuery(
                "SELECT r FROM " + Reservation.class.getName() + " r WHERE dateDebut >= :dateDebut AND dateFin <= :dateFin");
        q.setParameter("dateDebut", dateDebut);
        q.setParameter("dateFin", dateFin);
        return (List) q.getResultList();
    }
}
