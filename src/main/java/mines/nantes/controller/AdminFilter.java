package mines.nantes.controller;

import mines.nantes.dao.UtilisateurDAO;

/**
 * Created by mclaveau on 24/10/15.
 */
public class AdminFilter extends LoginFilter {

    @Override
    protected boolean isAuth() {
        return false;
    }

    @Override
    public void destroy() {

    }
}
