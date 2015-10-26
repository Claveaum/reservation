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
    <div class="alert alert-success" role="alert">${enregistrementMessage}</div>
</c:if>
<c:if test="${!periodeRenseignee}">
    <div class="container">
        <h2>Renseignez une période de recherche des réservations</h2>

        <form action="${pageContext.request.contextPath}/reservation/admin/reservationAdmin" class="form-horizontal" role="form" method="post">
            <div class="form-group">
                <label class="control-label col-md-5" for="dateDebut">Date de début de la réservation :</label>

                <div class="col-md-3">
                    <input type="text" name="dateDebutResa" value="${dateDebutResa}" class="form-control" id="dateDebut"
                           required="required"
                           placeholder="JJ/MM/AAAA">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-5" for="dateFin">Date de fin de la réservation :</label>

                <div class="col-md-3">
                    <input id="dateFin" name="dateFinResa" type="text" value="${dateFinResa}" class="form-control"
                           required="required"
                           placeholder="JJ/MM/AAAA">
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-4 col-md-2">
                    <button type="submit" class="btn btn-default">Rechercher</button>
                </div>
            </div>
        </form>
    </div>
</c:if>
<c:if test="${periodeRenseignee}">
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
        <div class="form-group">
            <a class="col-md-offset-5 btn btn-default" href="${pageContext.request.contextPath}/reservation/admin/reservationAdmin">Nouvelle recherche</a>
        </div>
    </div>


</c:if>