package social_media;

import java.util.HashMap;
import java.util.Map;

/**
 * A social media network that stores users and their friends.
 * @author rorycampbell, simonswopes, brodywilson
 */
public class Network {
    private Map<String, User> users; // A map of usernames to User objects

    /**
     * Creates a new Network object with an empty user map.
     */
    public Network() {
        this.users = new HashMap<>();
    }

    /**
     * Adds a user to the network's user map.
     *
     * @param user The user to add to the network.
     */
    public void addUser(User user) {
        this.users.put(user.getUsername(), user);
    }

    /**
     * Returns a user object from the network's user map.
     *
     * @param username The username of the user to retrieve.
     * @return The User object associated with the username, or null if the user doesn't exist in the network.
     */
    public User getUser(String username) {
        return this.users.get(username);
    }

    /**
     * Authenticates a user by checking if their password matches the one stored in the network.
     *
     * @param username The username of the user to authenticate.
     * @param password The password to check.
     * @return true if the password is correct for the given username, false otherwise.
     */
    public boolean authenticate(String username, String password) {
        User user = getUser(username);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    /**
     * Adds a friend to a user's friends list.
     *
     * @param user The user to add a friend to.
     * @param friend The user to add as a friend.
     */
    public void addFriend(User user, User friend) {
        user.addFriend(friend);
    }

    /**
     * Removes a friend from a user's friends list.
     *
     * @param user The user to remove a friend from.
     * @param friend The user to remove as a friend.
     */
    public void removeFriend(User user, User friend) {
        user.removeFriend(friend);
    }
}