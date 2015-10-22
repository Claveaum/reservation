package mines.nantes.dao;

import mines.nantes.Exception.UniciteException;
import mines.nantes.entity.Utilisateur;

import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Pierre on 21/10/2015.
 */
public class UtilisateurDAO extends AbstractDAO<Utilisateur> {

    public UtilisateurDAO() {
        super(Utilisateur.class);
    }

    /**
     * Sauvegarde une entité utilisateur
     * @param utilisateur
     * @throws UniciteException déclanchée si le login est déjà utilisé
     */
    @Override
    public void sauvegarder(Utilisateur utilisateur) throws UniciteException {
        try{
            super.sauvegarder(utilisateur);
        }
        catch (UniciteException e)
        {
            Manager.getEntityManager().getTransaction().rollback();
            throw new UniciteException("Le login "+utilisateur.getLogin()+" est déjà utilisé");
        }
    }
    /**
     * Retourne la liste de tous les utilisateurs
     * @return liste Utilisateur
     */
    public List<Utilisateur> getListeUtilisateur()
    {
        Query q = Manager.getEntityManager().createQuery(
                "SELECT u FROM " + Utilisateur.class.getName() + " u");
        return (List) q.getResultList();
    }

    /**
     * Retourne l'utilisateur correspondant au login / password. Si aucun utilisateur trouvé,
     * alors retourn null
     * @param login
     * @param password
     * @return l'Utilisateur correspondant login/password ou null s'il n'existe pas
     */
    public Utilisateur getUtilisateurParLoginPassword(String login, String password)
    {
        Query q = Manager.getEntityManager().createQuery(
                "SELECT u FROM " + Utilisateur.class.getName() + " u WHERE login = :login AND password = :password");
        q.setParameter("login", login);
        q.setParameter("password", password);
        List<Utilisateur> resultat = q.getResultList();
        if(resultat.size()==1)
        {
            return resultat.get(0);
        }
        else
        {
            return null;
        }
    }
}