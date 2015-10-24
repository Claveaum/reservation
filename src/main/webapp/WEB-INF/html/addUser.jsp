<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
  <title>Ajouter un utilisateur</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<div class="container">
  <h2>Ajouter un utilisateur</h2>
  <form action="/addUser" class="form-horizontal" role="form">
    <div class="form-group">
      <label class="control-label col-md-2" for="login">Login:</label>
      <div class="col-md-3">
        <input type="login" value="${utilisateur.login}" class="form-control" id="login" placeholder="Entrer un login">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-md-2" for="pwd">Password:</label>
      <div class="col-md-3">
        <input type="password" value="${utilisateur.password}" class="form-control" id="pwd" placeholder="Entrer un mot de passe">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-md-2" for="nom">Nom:</label>
      <div class="col-md-3">
        <input type="text" value="${utilisateur.nom}" class="form-control" id="nom" placeholder="Entrer un nom">
      </div>
    </div><div class="form-group">
    <label class="control-label col-md-2" for="prenom">Prénom:</label>
    <div class="col-md-3">
      <input type="text" value="${utilisateur.prenom}" class="form-control" id="prenom" placeholder="Entrer un prénom">
    </div>
  </div><div class="form-group">
    <label class="control-label col-md-2" for="email">Email:</label>
    <div class="col-md-3">
      <input type="text" value="${utilisateur.email}" class="form-control" id="email" placeholder="Entrer une adresse mail">
    </div>
  </div><div class="form-group">
    <label class="control-label col-md-2" for="telephone">Téléphone:</label>
    <div class="col-md-3">
      <input type="text" value="${utilisateur.telephone}" class="form-control" id="telephone" placeholder="Entrer un téléphone">
    </div>
  </div>



    <div class="form-group">
      <div class="col-md-offset-1 col-md-2">
        <button type="submit" class="btn btn-default">Ajouter un utilisateur</button>
      </div>
    </div>
  </form>
</div>



