/**
 * The SocialMediaUI class represents the user interface for the social media network.
 * It contains methods for handling user login, sign up, viewing and adding posts, 
 * viewing and adding friends, and viewing friends' activity feed.
 * @author rorycampbell, simonswopes, brodywilson
 */
package social_media;

import java.util.Scanner;
import java.util.*;

public class SocialMediaUI {
    private Network network;
    private User currentUser;
    private Scanner scanner;

    /**
     * Constructs a SocialMediaUI object by initializing the network and scanner.
     */
    public SocialMediaUI() {
        this.network = new Network();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Runs the social media network interface by displaying the login or user options
     * based on whether a user is currently logged in or not, and calling the appropriate
     * methods to handle the user's input.
     */
    public void run() {
        boolean running = true;
        int choice = -1;
        while (running) {
            // If no user is logged in, display login/signup options
            if (currentUser == null) {
                System.out.println("Welcome to Peeper!");
                System.out.println("1) Login");
                System.out.println("2) Sign up");
                System.out.println("3) Exit");

                try {
                	choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                }catch (InputMismatchException e){
                	choice = -1;
                	scanner.next();
                }

                switch (choice) {
                    case 1:
                        handleLogin();
                        break;
                    case 2:
                        handleSignup();
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                        break;
                }
            } else {
                // If a user is logged in, display user options
                System.out.println(currentUser.getUsername() + ":");
                System.out.println("1) View posts");
                System.out.println("2) Add post");
                System.out.println("3) View friends");
                System.out.println("4) Add friend");
                System.out.println("5) Remove friend");
                System.out.println("6) View friends activity feed");
                System.out.println("7) Logout");

                //try {
                	choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                //}catch (InputMismatchException e) {
                	//choice = -1;
                	//scanner.next();
                //}

                switch (choice) {
                    case 1:
                        handleViewPosts();
                        break;
                    case 2:
                        handleAddPost();
                        break;
                    case 3:
                        handleViewFriends();
                        break;
                    case 4:
                        handleAddFriend();
                        break;
                    case 5:
                        handleRemoveFriend();
                        break;
                    case 6:
                        handleViewFriendsActivityFeed();
                        break;
                    case 7:
                        currentUser = null;
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                        break;
                }
            }
        }
    }

    /**
     * Handles the user's input for logging in by prompting the user to enter their
     * username and password, and verifying the entered credentials with the network.
     */
    private void handleLogin() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (network.authenticate(username, password)) {
            currentUser = network.getUser(username);
            System.out.println("Login successful!\n\n");
        } else {
            System.out.println("Incorrect username or password.\n");
        }
    }
    /**
     * Handles the user's input for registering by prompting the user to 
     * enter their username, password, and email, 
     * ensuring emails end with "@ttu.edu", and 
     * passwords contain at least one special character,
     */
    private void handleSignup() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        
        String email = "";
        while (true) {
            try {
                System.out.println("Enter email:");
                email = scanner.nextLine();
                if (!email.endsWith("@ttu.edu")) {
                    throw new Exception("Email must end with @ttu.edu");
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid email: " + e.getMessage());
            }
        }
        
        String password = "";
        while (true) {
            try {
                System.out.println("Enter password:");
                password = scanner.nextLine();
                if (!password.matches(".*[!@#$%^&*()].*")) {
                    throw new Exception("Password must contain at least one special character");
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid password: " + e.getMessage());
            }
        }

        User newUser = new User(username, email, password);
        network.addUser(newUser);
        currentUser = newUser;

        System.out.println("Signup successful!\n\n");
    }

    /**
     * Handles the current user's list of posts via 'getPosts()' getter method
     * and uses 'getContent()' getter to display the content of each post
     */
    private void handleViewPosts() {
        List<Post> posts = currentUser.getPosts();
        if (posts.isEmpty()) {
            System.out.println("No posts to display.\n");
        } else {
            for (Post post : posts) {
                System.out.println(post.getContent());
            }
        }
    }
    
    /**
     * Creates new instance of Post object and adds it to the current user's 
     * list of posts via 'addPost'
     * post may not exceed 250 characterss
     */
    private void handleAddPost() {
    	while(true) {
    		try {
        		System.out.println("Enter post content:");
                String content = scanner.nextLine();
                if (content.length() > 250)
                	throw new Exception("Post may not exceed 250 characters!");
                Post newPost = new Post(content);
                currentUser.addPost(newPost);
                System.out.println("Post added!\n\n");
                break;
        	}catch (Exception e) {
        		System.out.println("Invalid Content: " + e.getMessage());
        	}
    	}
    }
    
    /**
     * Accesses the user's list of friends and prints each friend's username
     * via 'getFriends' and 'getUsername'
     */
    private void handleViewFriends() {
        List<User> friends = currentUser.getFriends();
        if (friends.isEmpty()) {
            System.out.println("You have no friends.\n");
        } else {
            for (User friend : friends) {
                System.out.println(friend.getUsername());
            }
        }
    }
    /**
     * Accesses the user's list of friends and shows each of their last three posts from
     * the list of posts they have in their User object
     */  
    private void handleViewFriendsActivityFeed() {
        List<User> friends = currentUser.getFriends();
        if (friends.isEmpty()) {
            System.out.println("You have no friends.\n");
            return;
        }

        System.out.println("Activity feed for " + currentUser.getUsername() + "'s friends");
        for (User friend : friends) {
            System.out.println(friend.getUsername() + ":");
            List<Post> posts = friend.getRecentPosts();
            if (posts.isEmpty()) {
                System.out.println("\tNo posts.\n");
            } else {
                for (Post post : posts) {
                    System.out.println("\t" + post.getContent() + "\n");
                }
            }
        }
    }

    /**
     * Searches the network (map of all users containing their account info) for inputed username.
     * if/else for if the user being searched does not exist or is already
     * in the current user's friend list
     */
    private void handleAddFriend() {
        System.out.println("Enter friend's username:");
        String username = scanner.nextLine();

        User friend = network.getUser(username);
        if (friend == null) {
            System.out.println("User not found.");
        } else if (currentUser.getFriends().contains(friend)) {
            System.out.println("You are already friends with " + friend.getUsername() + ".\n");
        } else {
            network.addFriend(currentUser, friend);
            System.out.println("Friend added!\n");
        }
    }
    /**
     *Exact same logic as 'handleAddFriend()'
     */
    private void handleRemoveFriend() {
        System.out.println("Enter friend's username:");
        String username = scanner.nextLine();

        User friend = network.getUser(username);
        if (friend == null) {
            System.out.println("User not found.\n");
        } else if (!currentUser.getFriends().contains(friend)) {
            System.out.println(friend.getUsername() + " is not your friend.\n");
        } else {
            network.removeFriend(currentUser, friend);
            System.out.println("Friend removed.\n");
        }
    }
}