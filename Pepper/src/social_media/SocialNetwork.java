package social_media;

import java.util.List;
import java.util.ArrayList;
import java.util.*;

/**
 * This class represents a social network that extends the basic network functionality.
 * It provides a method to get the recent activity feed of a user's friends.
 * @author rorycampbell, simonswopes, brodywilson
 */
public class SocialNetwork extends Network {

    /**
     * Returns the most recent activity feed of a user's friends.
     *
     * @param user the user to get the friends' activity feed for
     * @return a list of the most recent posts from the user's friends, sorted by date (most recent first)
     */
    public List<Post> getFriendActivityFeed(User user) {
        List<Post> recentPosts = new ArrayList<>();
        for (User friend : user.getFriends()) {
            recentPosts.addAll(friend.getRecentPosts());
        }
        Collections.sort(recentPosts, new Comparator<Post>() {
            public int compare(Post p1, Post p2) {
                return p2.getDate().compareTo(p1.getDate());
            }
        });
        return recentPosts.subList(0, Math.min(recentPosts.size(), 3));
    }
}