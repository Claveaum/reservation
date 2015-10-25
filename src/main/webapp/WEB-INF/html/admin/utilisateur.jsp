<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>Identifiant</th>
            <th>Nom d'utilisateur</th>
            <th>Mot de passe</th>
            <th>Prénom</th>
            <th>Nom</th>
            <th>Email</th>
            <th>Téléphone</th>
            <th>Admin</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listUser}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.prenom}</td>
                <td>${user.nom}</td>
                <td>${user.email}</td>
                <td>${user.telephone}</td>
                <td>${user.admin}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>