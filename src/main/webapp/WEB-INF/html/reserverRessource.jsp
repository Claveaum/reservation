<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
  <title>Réserver une ressource</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<div class="container">
  <form action="/reserverRessource" method="post" class="form-horizontal" role="form">
    <div class="form-group">
      <label class="control-label col-md-5" for="ressource">Sélectionner la ressource à réserver :</label>
      <div class="col-md-3">
        <select id="ressource" value="${ressource}" class="form-control" required="required">
          <option value="Type1">Type1</option>
          <option value="Type2">Type2</option>
          <c:forEach items="${listeRessource}" var="ressource">
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
</div>