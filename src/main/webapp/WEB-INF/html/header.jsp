<%--
  Created by IntelliJ IDEA.
  User: mclaveau
  Date: 30/09/15
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar"><span class="sr-only">Toggle navigation</span><span
                    class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
            <a class="navbar-brand" href="#"></a></div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/reservation/reservation">Reservation</a></li>
                <c:if test="${!sessionScope.user.admin}">
                    <li><a href="${pageContext.request.contextPath}/reservation/reservationUser">Liste des réservations</a></li>
                </c:if>
                <c:if test="${sessionScope.user.admin}">
                    <li><a href="${pageContext.request.contextPath}/reservation/admin/utilisateur">Utilisateur</a></li>
                    <li><a href="${pageContext.request.contextPath}/reservation/admin/ressource">Ressource</a></li>
                    <li><a href="${pageContext.request.contextPath}/reservation/admin/typeRessource">Type de ressource</a></li>
                    <li><a href="${pageContext.request.contextPath}/reservation/admin/reservationAdmin">Liste des réservations</a></li>
                </c:if>
                <li><a href="${pageContext.request.contextPath}/logout">Se déconnecter</a></li>
            </ul>
        </div>
        <!--/.nav-collapse --></div>
</nav>