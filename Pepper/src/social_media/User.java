package social_media;

import java.util.ArrayList;
import java.util.List;

/**
 * The User class represents a user of the social media platform.
 * This class implements the Postable interface, which defines methods for adding and retrieving posts.
 * @author rorycampbell
 * @author simonswopes
 * @author brodywilson.
 */
public class User implements Postable {
    private String username; // Add username field
    private String email;
    private String password;
    private List<Post> posts;
    private List<User> friends; // Add friends field

    /**
     * Constructs a User object with the given username, email, and password.
     * Initializes the user's posts and friends lists to empty ArrayLists.
     * @param username The user's username.
     * @param email The user's email address.
     * @param password The user's password.
     */
    public User(String username, String email, String password) { // Add username to constructor
        this.username = username;
        this.email = email;
        this.password = password;
        this.posts = new ArrayList<>(); // Initialize posts list
        this.friends = new ArrayList<>(); // Initialize friends list
    }
    
    /**
     * Gets the user's list of friends.
     * @return The user's friends list.
     */
    public List<User> getFriends() {
        return friends;
    }

    /**
     * Sets the user's list of friends to the given list.
     * @param friends The new list of friends.
     */
    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    /**
     * Adds a friend to the user's friends list.
     * @param friend The user to add as a friend.
     */
    public void addFriend(User friend) {
        this.friends.add(friend);
    }

    /**
     * Removes a friend from the user's friends list.
     * @param friend The user to remove as a friend.
     */
    public void removeFriend(User friend) {
        this.friends.remove(friend);
    }

    /**
     * Gets the user's username.
     * @return The user's username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the user's username to the given string.
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Gets the user's password.
     * @return The user's password.
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * Gets the user's email
     * @return The user's email
     */
    public String getEmail() {
    	return this.email;
    }

    /**
     * Adds a post to the user's posts list.
     * @param post The post to add.
     */
    public void addPost(Post post) {
        this.posts.add(post);
    }

    /**
     * Implements the getPosts method from the Postable interface.
     * @return The user's list of posts.
     */
    @Override
    public List<Post> getPosts() {
        return this.posts;
    }
    
    /**
     * Gets the user's three most recent posts.
     * @return A list containing the user's three most recent posts.
     */
    public List<Post> getRecentPosts() {
        List<Post> recentPosts = new ArrayList<>();
        int numPosts = Math.min(this.posts.size(), 3); // Get the number of posts to retrieve (no more than 3)
        for (int i = this.posts.size() - 1; i >= this.posts.size() - numPosts; i--) {
            recentPosts.add(this.posts.get(i)); // Add each post to the list
        }
        return recentPosts;
    }

    
}