package social_media;

import java.util.Date;

/**
 * A class representing a post made by a user.
 */
public class Post {
    private String content; // The content of the post
    private Date date; // The date the post was made

    /**
     * Constructs a new post with the given content and the current date.
     *
     * @param content the content of the post
     */
    public Post(String content) {
        this.content = content;
        this.date = new Date();
    }

    /**
     * @return the content of the post
     */
    public String getContent() {
        return content;
    }

    /**
     * @return the date the post was made
     */
    public Date getDate() {
        return date;
    }
}
