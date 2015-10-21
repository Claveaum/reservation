package mines.nantes.dao;

import mines.nantes.entity.Utilisateur;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Pierre on 21/10/2015.
 */
public class UtilisateurDAO extends AbstractDAO<Utilisateur> {

    public UtilisateurDAO() {
        super(Utilisateur.class);
    }

    public List<Utilisateur> getListeUtilisateur()
    {
        Query q = Manager.getEntityManager().createQuery(
                "SELECT u FROM " + Utilisateur.class.getName() + " u");
        return (List) q.getResultList();
    }
}
