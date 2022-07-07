package kz.bitlab.models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Comment {
    private Long id;

    private User user;

    private Blog blog;

    private String comment;

    private Timestamp postDate;

    SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    public Comment(){
    }


    public Comment(Long id, User user, Blog blog, String comment, Timestamp postDate) {
        this.id = id;
        this.user = user;
        this.blog = blog;
        this.comment = comment;
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

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPostDate() {
        return sdf3.format(postDate);
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }
}