package mines.nantes.controller.filter;

import mines.nantes.entity.Utilisateur;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mclaveau on 24/10/15.
 */
public class UserFilter extends LoginFilter {
    @Override
    protected boolean isAuth(HttpServletRequest req) {
        Utilisateur user = (Utilisateur) req.getSession().getAttribute("user");
        if (null != user) {
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
