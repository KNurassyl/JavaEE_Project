package kz.bitlab.models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Blog {
    private Long id;
    private User user;
    private String title;
    private String content;
    private Timestamp postDate;

    SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public Blog() {
    }
    public Blog(Long id, User user, String title, String content, Timestamp postDate) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.postDate = postDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostDate() {
        return sdf3.format(postDate);
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

}