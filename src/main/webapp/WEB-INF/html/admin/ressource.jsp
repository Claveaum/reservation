<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>Identifiant</th>
            <th>Nom</th>
            <th>Description</th>
            <th>Localisation</th>
            <th>Responsable</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listRessource}" var="ressource">
            <tr>
                <td>${ressource.id}</td>
                <td>${ressource.nom}</td>
                <td>${ressource.description}</td>
                <td>${ressource.localisation}</td>
                <td>${ressource.responsable.nom} ${ressource.responsable.prenom}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>