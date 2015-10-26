<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <h2>Réserver une ressource</h2>
    <c:if test="${erreur}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <span class="sr-only">Error:</span>
                ${messageErreur}
        </div>
    </c:if>
    <c:if test="${enregistrementOK}">
        <div class="alert alert-success" role="alert">${enregistrementMessage}</div>
        <a class="btn btn-default" href="/reservation/reservation">Retour</a>
    </c:if>
    <c:if test="${!enregistrementOK}">
        <form action="/reservation/reservationRecherche" method="post" class="form-horizontal" role="form">
            <div class="form-group">
                <label class="control-label col-md-5" for="typeRessource">Sélectionner le type de la ressource à
                    réserver:</label>

                <div class="col-md-3">
                    <select id="typeRessource" name="typeRessourceSelectionne" class="form-control"
                            required="required" ${rechercheEffectuee ? 'disabled=\"disabled\"' : ''}>
                        <c:forEach items="${typeRessourceListe}" var="typeRessource">
                            <option value="${typeRessource.id}" ${typeRessource.id == typeRessourceSelectionne ? 'selected=\"selected\"' : ''} >${typeRessource.nom}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-5" for="dateDebut">Date de début de la réservation :</label>

                <div class="col-md-3">
                    <input type="text" name="dateDebutResa" value="${dateDebutResa}" class="form-control" id="dateDebut"
                           required="required"
                           placeholder="JJ/MM/AAAA" ${rechercheEffectuee ? 'disabled=\"disabled\"' : ''}>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-5" for="dateFin">Date de fin de la réservation :</label>

                <div class="col-md-3">
                    <input id="dateFin" name="dateFinResa" type="text" value="${dateFinResa}" class="form-control"
                           required="required"
                           placeholder="JJ/MM/AAAA" ${rechercheEffectuee ? 'disabled=\"disabled\"' : ''}>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-3 col-md-2">
                    <button type="submit" class="btn btn-default" ${rechercheEffectuee ? 'disabled=\"disabled\"' : ''}>
                        Chercher les ressources disponibles
                    </button>
                </div>
            </div>
        </form>
        <c:if test="${rechercheEffectuee}">
            <form action="/reservation/reserverRessource" method="post" class="form-horizontal" role="form">
                <input name="dateDebutResa" value="${dateDebutResa}" type="hidden"/>
                <input name="dateFinResa" value="${dateFinResa}" type="hidden"/>

                <div class="form-group">
                    <label class="control-label col-md-5" for="ressource">Sélectionner la ressource à réserver :</label>

                    <div class="col-md-3">
                        <select id="ressource" name="ressourceSelectionnee" class="form-control" required="required">
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
    </c:if>
</div>