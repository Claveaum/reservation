<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">

    <h2>${modifier ? 'Modifier' : 'Ajouter'} une ressource</h2>

    <form action="${modifier ? '/admin/ressource/modifier' : '/admin/ressource/ajouter'}" method="post" class="form-horizontal" role="form">
        <input name="idRessource" value="${ressource.id}" hidden="hidden" />

        <div class="form-group">
            <label class="control-label col-md-3" for="typeRessource">Type de la ressource:</label>

            <div class="col-md-3">
                <select id="typeRessource" name="typeRessourceSelectionne" class="form-control" required="required">
                    <c:forEach items="${typeRessourceListe}" var="typeRessource">
                        <option value="${typeRessource.id}" ${typeRessource.id == ressource.type.id ? 'selected=\"selected\"' : ''}>${typeRessource.nom}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3" for="nom">Nom de la ressource:</label>
            <div class="col-md-3">
                <input type="text" name="nomRessource" value="${ressource.nom}" class="form-control" id="nom"
                       placeholder="Entrer un nom" required="required">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3" for="responsable">Responsable de la ressource:</label>

            <div class="col-md-3">
                <select id="responsable" name="responsableSelectionne" class="form-control" required="required">
                    <c:forEach items="${utilisateurListe}" var="responsable">
                        <option value="${responsable.id}" ${responsable.id == ressource.responsable.id ? 'selected=\"selected\"' : ''}>${responsable.prenom} ${responsable.nom}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3" for="description">Description :</label>
            <div class="col-md-3">
                <input id="description" name="descriptionRessource" type="text" value="${ressource.description}" class="form-control"
                       placeholder="Entrer une description" required="required">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3" for="localisation">Localisation :</label>
            <div class="col-md-3">
                <input id="localisation" name="localisationRessource" type="text" value="${ressource.localisation}" class="form-control"
                       placeholder="Entrer une localisation" required="required">
            </div>
        </div>
        <div class="form-group">
            <a class="btn btn-default col-md-offset-1 col-md-3" href="/admin/reservation/ressource">Retour à la liste des ressources</a>
            <div class="col-md-2">
                <button type="submit" class="btn btn-default">${modifier ? 'Modifier' : 'Ajouter'} une ressource</button>
            </div>
        </div>
    </form>
</div>
