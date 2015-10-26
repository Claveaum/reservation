<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <h2>${modifier ? 'Modifier' : 'Ajouter'} un utilisateur</h2>

    <form action="${pageContext.request.contextPath}${modifier ? '/admin/utilisateur/modifier' : '/admin/utilisateur/ajouter'}" method="post"
          class="form-horizontal"
          role="form">
        <input name="idUtilisateur" value="${utilisateur.id}" hidden="hidden"/>

        <div class="form-group">
            <label class="control-label col-md-2" for="login">Login:</label>

            <div class="col-md-3">
                <input type="login" name="login" value="${utilisateur.login}" class="form-control" id="login"
                       placeholder="Entrer un login" required="required">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-2" for="pwd">Password:</label>

            <div class="col-md-3">
                <input type="password" name="password" value="${utilisateur.password}" class="form-control" id="pwd"
                       placeholder="Entrer un mot de passe" required="required">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-2" for="nom">Nom:</label>

            <div class="col-md-3">
                <input type="text" name="nom" value="${utilisateur.nom}" class="form-control" id="nom"
                       placeholder="Entrer un nom" required="required">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-2" for="prenom">Prénom:</label>

            <div class="col-md-3">
                <input type="text" name="prenom" value="${utilisateur.prenom}" class="form-control" id="prenom"
                       placeholder="Entrer un prénom" required="required">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-2" for="email">Email:</label>

            <div class="col-md-3">
                <input type="text" name="email" value="${utilisateur.email}" class="form-control" id="email"
                       placeholder="Entrer une adresse mail" required="required">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-2" for="telephone">Téléphone:</label>

            <div class="col-md-3">
                <input type="text" name="telephone" value="${utilisateur.telephone}" class="form-control" id="telephone"
                       placeholder="Entrer un téléphone" required="required">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-2" for="admin">Administrateur:</label>

            <div class="col-md-3">
                <select id="admin" name="admin" class="form-control" required="required">
                    <option value="${false}" ${!utilisateur.admin ? 'selected=\"selected\"' : ''}>Non</option>
                    <option value="${true}" ${utilisateur.admin ? 'selected=\"selected\"' : ''}>Oui</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-offset-1 col-md-2">
                <button type="submit" class="btn btn-default">${modifier ? 'Modifier' : 'Ajouter'} un utilisateur
                </button>
            </div>
        </div>
    </form>
</div>



