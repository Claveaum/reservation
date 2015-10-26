<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<c:if test="${!reservationPresente}">
    <div class="alert alert-danger" role="alert">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <span class="sr-only">Error:</span>
        Vous n'avez effectuée aucune réservation
    </div>
</c:if>
<c:if test="${reservationPresente}">
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th>Nom de la ressource</th>
                <th>Date début réservation</th>
                <th>Date fin réservation</th>
                <th>Réservé par</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listeReservation}" var="resa">
                <tr>
                    <td>${resa.ressource.nom}</td>
                    <td><fmt:formatDate value="${resa.dateDebut}" pattern="dd/MM/yyy"/></td>
                    <td><fmt:formatDate value="${resa.dateFin}" pattern="dd/MM/yyy"/></td>
                    <td>${resa.utilisateur.prenom} ${resa.utilisateur.nom}</td>
                    <td><a href="${pageContext.request.contextPath}/annulerReservation/${resa.id}">Annuler</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>