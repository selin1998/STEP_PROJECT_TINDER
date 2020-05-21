
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


    <title>Sign up</title>

    <#include "css/bootstrap.min.css">
    <#include "css/style.css">
</head>

<body class="text-center">
<form class="form" method="post">
    <h1 class="h3 mb-3 font-weight-normal">${message}</h1>

    <label for="inputName" class="sr-only">Name</label>
    <input type="text" id="inputName" class="form-control" placeholder="Name" name="Name" required autofocus>
    <label for="inputName" class="sr-only">Surname</label>
    <input type="text" id="inputSurname" class="form-control" name="Surname" placeholder="Surname" required>

    <label for="photoLink" class="sr-only">Photo Link</label>
    <input type="text" id="photoLink" class="form-control" name="photolink" placeholder="Photo Link" required>

    <label for="inputJob" class="sr-only">Job</label>
    <input type="text" id="inputJob" class="form-control" placeholder="Job or position"  name="Job" required>

    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="email" id="inputEmail" class="form-control" placeholder="Email address" name="Email" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="Password" required>
    <#--    <label for="repeat_Password" class="sr-only">Repeat Password</label>-->
    <#--    <input type="password" id="repeatPassword" class="form-control" placeholder="Repeat Password" name="RepeatPassword" required>-->
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
    <p class="mt-5 mb-3 text-muted">&copy; Tinder 2020</p>
</form>
</body>
</html>