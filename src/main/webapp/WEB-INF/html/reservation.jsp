<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <h2>Réserver une ressource</h2>

    <form action="/reservation/reservation" method="post" class="form-horizontal" role="form">
        <div class="form-group">
            <label class="control-label col-md-5" for="typeRessource">Sélectionner le type de la ressource à réserver:</label>

            <div class="col-md-3">
                <select id="typeRessource" name="typeRessourceSelectionne" value="${typeRessourceSelectionne}" class="form-control" required="required">
                    <c:forEach items="${typeRessourceListe}" var="typeRessource">
                        <option value="${typeRessource.id}">${typeRessource.nom}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-5" for="dateDebut">Date de début de la réservation :</label>
            <div class="col-md-3">
                <input type="text" name="dateDebutResa" value="${dateDebutResa}" class="form-control" id="dateDebut" required="required"
                       placeholder="JJ/MM/AAAA">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-5" for="dateFin">Date de fin de la réservation :</label>
            <div class="col-md-3">
                <input id="dateFin" name="dateFinResa" type="text" value="${dateFinResa}" class="form-control" required="required"
                       placeholder="JJ/MM/AAAA">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-offset-3 col-md-2">
                <button type="submit" class="btn btn-default">Chercher les ressources disponibles</button>
            </div>
        </div>
    </form>
    <c:if test="${rechercheEffectuee}">
    <form action="/reserverRessource" method="post" class="form-horizontal" role="form">
        <div class="form-group">
            <label class="control-label col-md-5" for="ressource">Sélectionner la ressource à réserver :</label>
            <div class="col-md-3">
                <select id="ressource" value="${ressource}" class="form-control" required="required">
                    <c:forEach items="${ressourceDispoListe}" var="ressource">
                        <option value="${ressource.id}">${ressource.nom}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-offset-4 col-md-2">
                <button type="submit" class="btn btn-default">Reserver la ressource</button>
            </div>
        </div>
    </form>
    </c:if>
</div>