<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


    <title>People list</title>
    <#include "css/bootstrap.min.css">

    <#include "css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $(".clickable-row").click(function() {
                window.location = $(this).data("href");
            });
        });


    </script>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="mt-4">
                <div class="d-flex justify-content-center links">
                    <a href="/logout" class="ml-2">Logout</a>
                </div>
            </div>
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>
                </div>

                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                             <#list likedlist as likedOne>
                                <tr class="clickable-row" data-href="messages/${likedOne.user_id}">
                                <td width="20">
                                <div class="avatar-img">
                                <img class="img-circle small-image" src=${likedOne.photoLink}>  
                                </div>
                                </td>
                                <td class="align-middle">
                                ${likedOne.name}
                                 ${likedOne.surname}
                                </td>
                                 <td class="align-middle">
                                 ${likedOne.job}
                                 </td>
                                <td class="align-middle">
                                    Last Login: 6/10/2017<br>
                                    <small class="text-muted">5 days ago</small>
                                </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>