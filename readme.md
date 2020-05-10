## The task

Create MVP dating app Tinder

#### Technical requirements:
- When the application starts, the user must go to the login page.
- After entering the correct username and password, the user must go to the page `Users`, where he can see photos of other users one by one.
- Each photo has two buttons - Yes(Like) and No(Dislike). After pressing any of these buttons, the user is shown the next photo. The selection is remembered on the server.
- The user can go to the `Liked` page, where all other users will be visible, under which photos Yes(Like) was clicked.
- On the `Liked` page, you can open a dialog box and send a message to another user.
- Use Bootstrap templates as the basis for all web pages.
- Use Freemarker template to display HTML pages.
- Connect MySQL / PostgreSQL remote database to the application.

#### List of working endpoints in the ready-to-use application:
- /users
- /liked
- /messages/{id}
- /login

#### Additional technical requirements:
- Deploy the project to the Heroku server.

#### Used technologies:
- Jetty Core Server.
- Jetty Servlet Container.
- Java Template Engine Freemarker.
- Amazon RDS PostgreSQL.
- Bootstrap templates as the basis for all web pages.
- Cloud Application Platform Heroku.
