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
            <div class="alert alert-info" role="alert">${enregistrementMessage}</div>
        </c:if>
        <div class="col-md-offset-8 col-md-1" style="text-align: right">
            <a href="/admin/ressource/ajouter"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"/></a>
        </div>
        <div class="col-md-3">
            <a href="/admin/ressource/ajouter">
        <label class="control-label">Ajouter une ressource</label>
                </a>
        </div>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th>Nom</th>
            <th>Type de ressource</th>
            <th>Description</th>
            <th>Localisation</th>
            <th>Responsable</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listRessource}" var="ressource">
            <tr>
                <td>${ressource.nom}</td>
                <td>${ressource.type.nom}</td>
                <td>${ressource.description}</td>
                <td>${ressource.localisation}</td>
                <td>${ressource.responsable.nom} ${ressource.responsable.prenom}</td>
                <td><a href="/admin/ressource/modifier/${ressource.id}"><span class="glyphicon glyphicon-pencil" aria-hidden="true"/></a></td>
                <td><a href="/admin/ressource/supprimer/${ressource.id}"><span class="glyphicon glyphicon-remove" aria-hidden="true"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>