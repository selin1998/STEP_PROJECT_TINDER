<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Chat</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <#include "css/bootstrap.min.css">

    <#include "css/style.css">
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
        <div class="chat-main col-6 offset-3">
            <div class="col-md-12 chat-header">
                <div class="row header-one text-white p-1">

                    <div class="col-md-6 name pl-2">
                        <i class="fa fa-comment"></i>
                        <h6 class="ml-1 mb-0">${targetUser.name} ${targetUser.surname}</h6>
                    </div>
                    <div class="col-md-6 options text-right pr-0">
                        <i class="fa fa-window-minimize hide-chat-box hover text-center pt-1"></i>
                        <p class="arrow-up mb-0">
                            <i class="fa fa-arrow-up text-center pt-1"></i>
                        </p>
                        <i class="fa fa-times hover text-center pt-1"></i>
                    </div>
                </div>
                <div class="row header-two w-100">
                    <div class="col-md-6 options-left pl-1">
                        <i class="fa fa-video-camera mr-3"></i>
                        <i class="fa fa-user-plus"></i>
                    </div>
                    <div class="col-md-6 options-right text-right pr-2">
                        <i class="fa fa-cog"></i>
                    </div>
                </div>
            </div>
            <div class="chat-content">
                <div class="col-md-12 chats pt-3 pl-2 pr-3 pb-3">
                    <ul class="p-0">
                        <#list messages as msg >
                            <#if msg.user_id_from == loggedUser.user_id>
                                <li class="send-msg float-right mb-2">
                                    <div class="sender-img">
                                        <img src="${loggedUser.photoLink}" class="float-right">
                                    </div>
                                    <div class="receive-msg-desc float-right ml-2">
                                            <p class="pt-1 pb-1 pl-2 pr-2 m-0 rounded">
                                             ${msg.message}
                                            </p>
                                    <span class="receive-msg-time">${loggedUser.name}, ${msg.getTimeString()}</span>
                                    </div>
                                </li>
                            <#else>
                                <li class="receive-msg float-left mb-2">
                                    <div class="sender-img">
                                         <img src="${targetUser.photoLink}" class="float-left">
                                    </div>
                                    <div class="receive-msg-desc float-left ml-2">
                                            <p class="bg-white m-0 pt-1 pb-1 pl-2 pr-2 rounded">
                                            ${msg.message}
                                            </p>
                                    <span class="receive-msg-time">${targetUser.name}, ${msg.getTimeString()}</span>
                                    </div>
                                </li>
                            </#if>
                        </#list>
                    </ul>
                </div>
                <form action="/messages/${targetUser.user_id}" method="POST">
                <div class="col-md-12 p-2 msg-box border border-primary">
                    <div class="row">
                        <div class="col-md-2 options-left">
                            <i class="fa fa-smile-o"></i>
                        </div>
                        <div class="col-md-7 pl-0">
                            <input name="input" type="text" class="border-0" placeholder="Press enter" required/>
                        </div>
                        <div class="col-md-3 text-right options-right">
                            <i class="fa fa-picture-o mr-2"></i>
                        </div>
                    </div>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>