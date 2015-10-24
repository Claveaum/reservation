<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Ajouter une ressource</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<div class="container">
    <h2>Ajouter une ressource</h2>

    <form action="/addRessource" class="form-horizontal" role="form">
        <div class="form-group">
            <label class="control-label col-md-3" for="typeRessource">Type de la ressource:</label>

            <div class="col-md-3">
                <select id="typeRessource" value="${ressource.type}" class="form-control">
                    <option value="Type1">Type1</option>
                    <option value="Type2">Type2</option>
                    <c:forEach items="${typeRessourceListe}" var="typeRessource">
                        <option value="${typeRessource.id}">${typeRessource.nom}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3" for="nom">Nom de la ressource:</label>
            <div class="col-md-3">
                <input type="text" value="${ressource.nom}" class="form-control" id="nom"
                       placeholder="Entrer un nom">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3" for="responsable">Responsable de la ressource:</label>

            <div class="col-md-3">
                <select id="responsable" value="${ressource.responsable}" class="form-control">
                    <option value="Type1">user1</option>
                    <option value="Type2">user2</option>
                    <c:forEach items="${utilisateurListe}" var="responsable">
                        <option value="${responsable.id}">${responsable.prenom + " " + responsable.nom}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3" for="description">Description :</label>
            <div class="col-md-3">
                <input id="description" type="text" value="${ressource.description}" class="form-control"
                       placeholder="Entrer une description">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3" for="localisation">Localisation :</label>
            <div class="col-md-3">
                <input id="localisation" type="text" value="${ressource.localisation}" class="form-control"
                       placeholder="Entrer une localisation">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-offset-1 col-md-2">
                <button type="submit" class="btn btn-default">Ajouter une ressource</button>
            </div>
        </div>
    </form>
</div>
