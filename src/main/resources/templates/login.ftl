
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


    <title>Sign in</title>

    <#include "css/bootstrap.min.css">
    <#include "css/style.css">

</head>

<body class="text-center">

<form class="form" method="post">
    <h1 class="h3 mb-3 font-weight-normal">${message}</h1>
        <label for="Email" class="sr-only">Email</label>
        <input class="form-control" type="text" name="Email" placeholder="Email">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="Password" userId="inputPassword" class="form-control" placeholder="Password">
        <input class="btn btn-lg btn-primary btn-block"  class="submit" type="submit" value="Login">
    <#--    <label><a href="/login" class="btn btn-primary btn-block">Log in.</a></label>-->
    <div class="or">or</div>
    <label><a href="/signup">Don't have account yet? Click to register.</a></label>
    <p class="mt-5 mb-3 text-muted">&copy; Tinder 2020</p>
</form>

</body>
</html>