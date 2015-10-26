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
            <a href="/admin/utilisateur/ajouter"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"/></a>
        </div>
        <div class="col-md-3">
            <a href="/admin/utilisateur/ajouter">
                <label class="control-label">Ajouter un utilisateur</label>
            </a>
        </div>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th>Nom d'utilisateur</th>
            <th>Mot de passe</th>
            <th>Prénom</th>
            <th>Nom</th>
            <th>Email</th>
            <th>Téléphone</th>
            <th>Admin</th>
            <th></th>
            <th></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listUser}" var="user">
            <tr>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.prenom}</td>
                <td>${user.nom}</td>
                <td>${user.email}</td>
                <td>${user.telephone}</td>
                <td>${user.admin}</td>
                <td><a href="/admin/utilisateur/modifier/${user.id}"><span class="glyphicon glyphicon-pencil"
                                                                           aria-hidden="true"/></a></td>
                <td><a href="/admin/utilisateur/supprimer/${user.id}"><span class="glyphicon glyphicon-remove"
                                                                            aria-hidden="true"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>