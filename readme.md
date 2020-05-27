## MVP Dating App Tinder

The functionality of app is as follows: You sign up by providing your e-mail,password,name,title,image url from Internet. If you are already registered you may simply sign in. You are then directed to like-dislike page where can choose users with whom you want to chat. Your preferences are saved even after you quit the app. You are free to dislike user you liked before and vice-versa. After sliding through all of the users you are directed to My Mate List-> there you may see name, status and last activity time of your mates. Last activity is the time you were last seen-your logout time. When you click on the row with the user you may switch to chat. All send messages come with user image and the time it was sended. Once you finish you may log out.

## Database Credentials

 URL = "jdbc:postgresql://ec2-54-246-89-234.eu-west-1.compute.amazonaws.com:5432/d87hm9l72493m5";
 NAME = "nqgaomrdgooiuc";
 PWD = "6bc08a423fd1532490c7a3f43410f90b43b12a3e26adeb82dc856cd6279ad35e";


#### List of working endpoints in the ready-to-use application:
- /users
- /liked
- /messages/{id}
- /login

#### Some credentials of users if you are reluctant to sign up

1. login: qq@qq.qq            password: qq
2. login: 221baker@inbox.com  password: benedictus221b
3. login: jupiter@gmail.com   password: jupiter5


#### Link to working application

http://tinder-app-step-project.herokuapp.com/login

#### Used technologies:
- Jetty Core Server.
- Jetty Servlet Container.
- Java Template Engine Freemarker.
- PostgreSQL.
- Bootstrap templates as the basis for all web pages.
- Cloud Application Platform Heroku.
