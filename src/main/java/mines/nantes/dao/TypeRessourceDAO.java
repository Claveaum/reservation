package mines.nantes.dao;

import mines.nantes.Exception.UniciteException;
import mines.nantes.entity.TypeRessource;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Pierre on 21/10/2015.
 */
public class TypeRessourceDAO extends AbstractDAO<TypeRessource> {

    public TypeRessourceDAO() {
        super(TypeRessource.class);
    }

    /**
     * Sauvegarde une entité TypeRessource
     * @param typeRessource
     * @throws UniciteException déclanchée si le nom est déjà utilisé
     */
    @Override
    public void sauvegarder(TypeRessource typeRessource) throws UniciteException
    {
        try{
            super.sauvegarder(typeRessource);
        }
        catch (UniciteException e) {
            Manager.getEntityManager().getTransaction().rollback();
            throw new UniciteException("Le type de ressource " + typeRessource.getNom() + " existe déjà");
        }
    }

    /**
     * Retourne le type de ressource correspondant au nom, s'il n'existe pas, retourne null
     * @param nom
     * @return typeRessource correspondant OU null
     */
    public TypeRessource getTypeRessourceParNom(String nom)
    {
        Query q = Manager.getEntityManager().createQuery(
                "SELECT tr FROM " + TypeRessource.class.getName() + " tr WHERE tr.nom = :nom");
        q.setParameter("nom",nom);
        List<TypeRessource> resultat = q.getResultList();
        if(resultat.size()==1)
        {
            return resultat.get(0);
        }
        else {
            return null;
        }
    }

    /**
     * Retourne la totalité des types de ressources
     * @return
     */
    public List<TypeRessource> getListeTypeRessource()
    {
        Query q = Manager.getEntityManager().createQuery(
                "SELECT tr FROM " + TypeRessource.class.getName() + " tr");
        return (List) q.getResultList();
    }
}
