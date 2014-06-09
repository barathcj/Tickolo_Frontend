Tickolo is a fan based tickets resales platform. It allows users to post tickets on the app for a price of face value or below.
(This will be manually checked). The app allows buyers to report sellers violating this policy.
Currently the app does not support user login as facebook login takes really long on the emulator.

The app accesses a server on the local host. Hence it will not run if the server is not present. The server side files are written in PHP and have been
placed in a folder named 'php_serverside' inside the zip folder. There was one table made on the server which contains details about the tickets being
sold. (This was made using the phpmyadmin UI). XAMPP was used to host the server.

In order to run the app, one has to download and install xampp. Create a database called 'tickolo_database'. Then create a table called 'tickets' with the 
following columns 'id', 'event_name', 'event_type', 'section', 'row', 'quantity', 'price', 'facevalue', 'event_date' . The php files have to be places in the
htdocs folder which will be inside the xampp folder.

Apart from that online resources were consulted for writing the android code from popular websites (stackoverflow etc..)
Image icons are also taken from the web.
 