package mines.nantes.dao;

import mines.nantes.entity.Reservation;
import mines.nantes.entity.Ressource;
import mines.nantes.entity.TypeRessource;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by Pierre on 21/10/2015.
 */
public class RessourceDAO extends AbstractDAO<Ressource> {

    public RessourceDAO() {
        super(Ressource.class);
    }

    /**
     * Retourne la totalité des ressources
     * @return
     */
    public List<Ressource> getListeRessource()
    {
        Query q = Manager.getEntityManager().createQuery(
                "SELECT r FROM " + Ressource.class.getName() + " r");
        return (List) q.getResultList();
    }

    /**
     * Retourne une liste de ressource correspondant à un type ET non réservée pendant la période donnée
     * @param typeRessource
     * @param dateDebut
     * @param dateFin
     * @return
     */
    public List<Ressource> getRessourcesAvecTypeLibres(TypeRessource typeRessource, Date dateDebut, Date dateFin)
    {
        Query q = Manager.getEntityManager().createQuery(
                "SELECT r FROM " + Ressource.class.getName() + " r INNER JOIN r.listeReservation resa WHERE r.type = :typeRessource" +
                        " AND (resa.dateDebut > :dateFin OR resa.dateFin < :dateDebut)");
        q.setParameter("typeRessource", typeRessource);
        q.setParameter("dateDebut", dateDebut);
        q.setParameter("dateFin", dateFin);
        return q.getResultList();
    }

    /**
     * Retourne la liste des ressources ayant des réservations futures
     * @param dateCourante
     * @return
     */
    public List<Ressource> getRessourcesAvecResaFuture(Date dateCourante)
    {
        Query q = Manager.getEntityManager().createQuery(
                "SELECT r FROM " + Ressource.class.getName() + " r INNER JOIN r.listeReservation resa WHERE resa.dateDebut > :dateDebut");
        q.setParameter("dateDebut", dateCourante);
        return q.getResultList();
    }
}
