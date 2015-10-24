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
  <h2>Réserver une ressource</h2>

  <form action="/chercherRessource" method="post" class="form-horizontal" role="form">
    <div class="form-group">
      <label class="control-label col-md-5" for="typeRessource">Sélectionner le type de la ressource à réserver:</label>

      <div class="col-md-3">
        <select id="typeRessource" value="${ressource.type}" class="form-control" required="required">
          <option value="Type1">Type1</option>
          <option value="Type2">Type2</option>
          <c:forEach items="${typeRessourceListe}" var="typeRessource">
            <option value="${typeRessource.id}">${typeRessource.nom}</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-md-5" for="dateDebut">Date de début de la réservation :</label>
      <div class="col-md-3">
        <input type="text" value="${dateDebut}" class="form-control" id="dateDebut" required="required"
               placeholder="JJ/MM/AAAA">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-md-5" for="dateFin">Date de fin de la réservation :</label>
      <div class="col-md-3">
        <input id="dateFin" type="text" value="${dateFin}" class="form-control" required="required"
               placeholder="JJ/MM/AAAA">
      </div>
    </div>
    <div class="form-group">
      <div class="col-md-offset-3 col-md-2">
        <button type="submit" class="btn btn-default">Chercher les ressources disponibles</button>
      </div>
    </div>
  </form>
</div>