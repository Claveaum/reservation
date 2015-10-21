package mines.nantes.dao;

/**
 * Created by Pierre on 21/10/2015.
 */
public interface DAO<E> {
    E trouverParId(int id);
    void sauvegarder(E entite);
    void supprimer(E entite);

}
