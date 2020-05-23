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
<body style="background-color:#FE3C72;">

<div class="container">
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
        <h5 class="my-0 mr-md-auto font-weight-normal"><img src="https://mk0brandpalettefeufc.kinstacdn.com/wp-content/uploads/2018/10/TinderLogo-300x72.png" alt="Tinder Brand colors" width="300" height="72" class="alignnone size-medium wp-image-737 lazyloaded" data-lazy-srcset="https://mk0brandpalettefeufc.kinstacdn.com/wp-content/uploads/2018/10/TinderLogo-300x72.png 300w, https://mk0brandpalettefeufc.kinstacdn.com/wp-content/uploads/2018/10/TinderLogo-768x184.png 768w, https://mk0brandpalettefeufc.kinstacdn.com/wp-content/uploads/2018/10/TinderLogo.png 833w" data-lazy-sizes="(max-width: 300px) 100vw, 300px" data-lazy-src="https://mk0brandpalettefeufc.kinstacdn.com/wp-content/uploads/2018/10/TinderLogo-300x72.png" sizes="(max-width: 300px) 100vw, 300px" srcset="https://mk0brandpalettefeufc.kinstacdn.com/wp-content/uploads/2018/10/TinderLogo-300x72.png 300w, https://mk0brandpalettefeufc.kinstacdn.com/wp-content/uploads/2018/10/TinderLogo-768x184.png 768w, https://mk0brandpalettefeufc.kinstacdn.com/wp-content/uploads/2018/10/TinderLogo.png 833w" data-was-processed="true"></h5>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="/users">Find mate</a>
            <a class="p-2 text-dark" href="/liked">My mate list</a>
            <a class="p-2 text-dark" href="/logout">Logout</a>
        </nav>

    </div>
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
                                    <#list lastlogin as lastlog>
                                        <#if lastlog.user_id == likedOne.user_id>
                                            Last Activity: ${lastlog.getLogout_time_String()}<br>
                                            <small class="text-muted">${lastlog.howMuchTimeAgo()}</small>
                                        </#if>
                                    </#list>
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