<%--
  Created by IntelliJ IDEA.
  User: Pierre
  Date: 25/10/2015
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
  <h2>${modifier ? 'Modifier' : 'Ajouter'} un type de ressource</h2>

  <form action="${modifier ? '/admin/typeRessource/modifier' : '/admin/typeRessource/ajouter'}" method="post" class="form-horizontal"
        role="form">
    <input name="idTypeRessource" value="${typeRessource.id}" hidden="hidden"/>


    <div class="form-group">
      <label class="control-label col-md-2" for="nom">Nom:</label>

      <div class="col-md-3">
        <input type="text" name="nom" value="${typeRessource.nom}" class="form-control" id="nom"
               placeholder="Entrer un nom" required="required">
      </div>
    </div>

    <div class="form-group">
      <div class="col-md-offset-1 col-md-2">
        <button type="submit" class="btn btn-default">${modifier ? 'Modifier' : 'Ajouter'} un type de ressource</button>
      </div>
    </div>
  </form>
</div>
