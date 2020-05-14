<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>People list</title>
    <#include "css/bootstrap.min.css">

    <#include "css/style.css">

</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                             <#list likedlist as likedOne>
                                <tr class="clickable-row" data-href="messages/${likedOne.login}">
                                <td width="10">
                                    <!--<h1>${likedOne.name} ${likedOne.surname}</h1>-->
                                <div class="avatar-img">
                                <img class="img-circle small-image" src=${likedOne.photoLink}>  
                                </div>
                                </td>
                                <td class="align-middle">
                                ${likedOne.name}
                                </td>
                                <td class="align-middle">
                                ${likedOne.surname}
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