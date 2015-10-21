package mines.nantes.dao;

import mines.nantes.entity.Ressource;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Pierre on 21/10/2015.
 */
public class RessourceDAO extends AbstractDAO<Ressource> {

    public RessourceDAO() {
        super(Ressource.class);
    }

    public List<Ressource> getListeRessource()
    {
        Query q = Manager.getEntityManager().createQuery(
                "SELECT r FROM " + Ressource.class.getName() + " r");
        return (List) q.getResultList();
    }
}
