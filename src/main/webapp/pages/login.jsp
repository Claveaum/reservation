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
    <form action="/loginCheck" class="form-horizontal" role="form">
        <div class="form-group">
            <label class="control-label col-md-2" for="login">Login:</label>
            <div class="col-md-3">
                <input type="login" value="${utilisateur.login}" class="form-control" id="login" placeholder="Entrer votre login">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-2" for="pwd">Password:</label>
            <div class="col-md-3">
                <input type="password" value="${utilisateur.password}" class="form-control" id="pwd" placeholder="Entrer votre mot de passe">
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
