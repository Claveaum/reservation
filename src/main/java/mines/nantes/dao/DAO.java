package mines.nantes.dao;

import mines.nantes.Exception.UniciteException;

/**
 * Created by Pierre on 21/10/2015.
 */
public interface DAO<E> {
    E trouverParId(int id);
    void sauvegarder(E entite) throws UniciteException;
    void supprimer(E entite);

}
