<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


    <title>Like page</title>
    <#include "css/bootstrap.min.css">

    <#include "css/style.css">
</head>
<body style="background-color: #FE3C72;">


<div class="container">

    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
        <h5 class="my-0 mr-md-auto font-weight-normal"><img src="https://mk0brandpalettefeufc.kinstacdn.com/wp-content/uploads/2018/10/TinderLogo-300x72.png" alt="Tinder Brand colors" width="300" height="72" class="alignnone size-medium wp-image-737 lazyloaded" data-lazy-srcset="https://mk0brandpalettefeufc.kinstacdn.com/wp-content/uploads/2018/10/TinderLogo-300x72.png 300w, https://mk0brandpalettefeufc.kinstacdn.com/wp-content/uploads/2018/10/TinderLogo-768x184.png 768w, https://mk0brandpalettefeufc.kinstacdn.com/wp-content/uploads/2018/10/TinderLogo.png 833w" data-lazy-sizes="(max-width: 300px) 100vw, 300px" data-lazy-src="https://mk0brandpalettefeufc.kinstacdn.com/wp-content/uploads/2018/10/TinderLogo-300x72.png" sizes="(max-width: 300px) 100vw, 300px" srcset="https://mk0brandpalettefeufc.kinstacdn.com/wp-content/uploads/2018/10/TinderLogo-300x72.png 300w, https://mk0brandpalettefeufc.kinstacdn.com/wp-content/uploads/2018/10/TinderLogo-768x184.png 768w, https://mk0brandpalettefeufc.kinstacdn.com/wp-content/uploads/2018/10/TinderLogo.png 833w" data-was-processed="true"></h5>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="/users">Find mate</a>
            <a class="p-2 text-dark" href="/liked">My mate list</a>
            <a class="p-2 text-dark" href="/logout">Logout</a>

        </nav>

    </div>



    <div class="col-6 offset-3">
            <form class="form-users" action="/users" method="post">

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