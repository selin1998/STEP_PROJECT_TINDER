<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Like page</title>
    <#include "css/bootstrap.min.css">

    <#include "css/style.css">
</head>
<body style="background-color: #f5f5f5;">

<div class="col-4 offset-4">
    <form class="form-users" action="/users" method="post">
        <div class="mt-4">
            <div class="d-flex justify-content-center links">
                <a href="/logout" class="ml-2">Logout</a>
            </div>
        </div>
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-12 col-lg-12 col-md-12 text-center">
                    <img src=${user.photoLink} class="mx-auto rounded-circle img-fluid">
                    <h3 class="mb-0 text-truncated">${user.name}  ${user.surname}</h3>
                    <br>
                </div>
                <div class="col-12 col-lg-6">
                    <button name="dislike" type="submit" class="btn btn-outline-success btn-block"><span class="fa fa-heart"></span> Dislike</button>
                </div>
                <div class="col-12 col-lg-6">
                    <button name="like" type="submit" class="btn btn-outline-danger btn-block"><span class="fa fa-times"></span> Like</button>
                </div>

                <!--/col-->
            </div>
        </div>
            <!--/row-->
        </div>
        <!--/card-block-->
    </div>
</div>

</body>
</html>