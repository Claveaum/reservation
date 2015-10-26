<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">
    <div class="form-group">
        <c:if test="${erreur}">
            <div class="alert alert-danger" role="alert">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                <span class="sr-only">Error:</span>
                    ${messageErreur}
            </div>
        </c:if>
        <c:if test="${enregistrementOK}">
            <div class="alert alert-success" role="alert">${enregistrementMessage}</div>
        </c:if>
        <c:if test="${alerte}">
            <form method="get" action="/admin/typeRessource/supprimerValider/${typeRessource.id}">
                <div class="alert alert-warning" role="alert">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <span class="sr-only">Alerte:</span>
                        ${messageAlerte}
                </div>
                <div class="col-md-12" style="text-align: center">
                    <button type="submit" class="btn btn-default">Valider la suppression de "${typeRessource.nom}"
                    </button>
                </div>
            </form>
        </c:if>
        <c:if test="${!alerte}">

            <div class="col-md-offset-8 col-md-1" style="text-align: right">
                <a href="/admin/typeRessource/ajouter"><span class="glyphicon glyphicon-plus-sign"
                                                             aria-hidden="true"/></a>
            </div>
            <div class="col-md-3">
                <a type="submit" href="/admin/typeRessource/ajouter">
                    <label class="control-label">Ajouter un type de ressource</label>
                </a>
            </div>
        </c:if>
    </div>
    <c:if test="${!alerte}">
        <table class="table">
            <thead>
            <tr>
                <th>Nom du type de ressource</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listeTypeRessource}" var="typeRessource">
                <tr>
                    <td>${typeRessource.nom}</td>
                    <td><a href="/admin/typeRessource/modifier/${typeRessource.id}"><span
                            class="glyphicon glyphicon-pencil"
                            aria-hidden="true"/></a></td>
                    <td><a href="/admin/typeRessource/supprimer/${typeRessource.id}"><span
                            class="glyphicon glyphicon-remove" aria-hidden="true"/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>