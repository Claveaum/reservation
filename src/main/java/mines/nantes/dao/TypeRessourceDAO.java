package mines.nantes.dao;

import mines.nantes.entity.TypeRessource;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Pierre on 21/10/2015.
 */
public class TypeRessourceDAO extends AbstractDAO<TypeRessource> {

    public TypeRessourceDAO() {
        super(TypeRessource.class);
    }

    public List<TypeRessource> getListeTypeRessource()
    {
        Query q = Manager.getEntityManager().createQuery(
                "SELECT r FROM " + TypeRessource.class.getName() + " r");
        return (List) q.getResultList();
    }
}
