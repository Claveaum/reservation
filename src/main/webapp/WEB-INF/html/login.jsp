<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Site de Réservation</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Identifiez-vous</h2>
    <c:if test="${erreur}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <span class="sr-only">Error:</span>
                ${messageErreur}
        </div>
    </c:if>
    <form action="/loginCheck" class="form-horizontal" role="form" method="post">
        <div class="form-group">
            <label class="control-label col-md-2" for="login">Login:</label>
            <div class="col-md-3">
                <input type="login" name="login" class="form-control" id="login" placeholder="Entrer votre login">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-2" for="password">Password:</label>
            <div class="col-md-3">
                <input type="password" name="password" class="form-control" id="password" placeholder="Entrer votre mot de passe">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-offset-1 col-md-2">
                <button type="submit" class="btn btn-default">Se connecter</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
