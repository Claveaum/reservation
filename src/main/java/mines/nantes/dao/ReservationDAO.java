package mines.nantes.dao;

import mines.nantes.entity.Reservation;

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

    public List<Reservation> getListeReservation()
    {
        Query q = Manager.getEntityManager().createQuery(
                "SELECT r FROM " + Reservation.class.getName() + " r");
        return (List) q.getResultList();
    }

    public List<Reservation> getReservationParPeriode(Date dateDebut, Date dateFin)
    {
        Query q = Manager.getEntityManager().createQuery(
                "SELECT r FROM " + Reservation.class.getName() + " r WHERE dateDebut >= :dateDebut AND dateFin <= :dateFin");
        q.setParameter("dateDebut", dateDebut);
        q.setParameter("dateFin", dateFin);
        return (List) q.getResultList();
    }
}
