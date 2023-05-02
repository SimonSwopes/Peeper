package social_media;

import java.util.List;

/**
 * This interface represents an object that is capable of retrieving a list of posts.
 * 
 * @author RoryCampbell, SimonSwopes, and BrodyWilson
 */
public interface Postable {

    /**
     * @return A list of posts made by this object.
     */
    List<Post> getPosts();
}
