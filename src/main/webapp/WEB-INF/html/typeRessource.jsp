<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>Identifiant</th>
            <th>Nom</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listTypeRessource}" var="typeRessource">
            <tr>
                <td>${typeRessource.id}</td>
                <td>${typeRessource.nom}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>