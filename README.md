# Peeper
A small school project that asked us to use object orientated principles to simulate a social network in JAVA. 
This project was written by Simon Swopes, Rory Campbell, and Brody Wilson at Texas Tech University
This was simply a final project made in under a week so no GUI has been implemented other than a menu selector in the Terminal
This project also currenly has no way to save a user or posts once the program has been terminated.
The main function simply creates an instance of the SocialMediaUI class. This is where the user will be most directly interacting. When the user selects 
a menu option such as log in a switch statement calls a handler which will work with the other classes. A user is represented as an instance of the User
class, a post is an instance of the Post Class. To meet project requirements we added an interface called postable. The network is the main manager
and is the back bone of the proct. It ties user's and their post to their friends. It is where all users and post can be accessed. But an individual user
can only see their and their friends post.
