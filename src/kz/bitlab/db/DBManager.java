package kz.bitlab.db;

import kz.bitlab.models.Blog;
import kz.bitlab.models.Comment;
import kz.bitlab.models.User;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;

    static {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee_project?useUnicode=true&serverTimezone=UTC",
                    "root", ""
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User getUser(String email) {
        User user = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE email = ? LIMIT 1"
            );
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name")
                );
            }

            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public static boolean addUser(User user) {
        int rows = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (id, email, password, full_name) VALUES (NULL, ?, ?, ?)"
            );

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());

            rows = preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return rows > 0;
    }

    public static boolean addBlog(Blog blog) {
        int rows = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO blogs (id, user_id, title, content, post_date)" +
                            " VALUES (NULL, ?, ?, ?, ?)"
            );

            preparedStatement.setLong(1, blog.getUser().getId());
            preparedStatement.setString(2, blog.getTitle());
            preparedStatement.setString(3, blog.getContent());
            preparedStatement.setTimestamp(4, Timestamp.from(Instant.now()));

            rows = preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return rows > 0;
    }

    public static ArrayList<Blog> getAllBlogs() {
        ArrayList<Blog> blogs = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT blog.id, blog.user_id, blog.title, blog.content, blog.post_date, us.email, us.full_name" +
                            " FROM blogs blog INNER JOIN users us ON blog.user_id = us.id ORDER BY blog.post_date DESC"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Blog blog = new Blog();
                blog.setId(resultSet.getLong("id"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setPostDate(resultSet.getTimestamp("post_date"));
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setEmail(resultSet.getString("email"));
                user.setFullName(resultSet.getString("full_name"));
                blog.setUser(user);
                blogs.add(blog);
            }

            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return blogs;
    }

    public static Blog getBlog(Long id) {
        Blog blog = null;
        User user = new User();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT blog.id, blog.user_id, blog.title, blog.content, blog.post_date, us.email, us.full_name" +
                            " FROM blogs blog INNER JOIN users us ON blog.user_id = us.id WHERE blog.id = ?"
            );
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setId(resultSet.getLong("user_id"));
                user.setEmail(resultSet.getString("email"));
                user.setFullName(resultSet.getString("full_name"));
                blog = new Blog(
                        resultSet.getLong("id"),
                        user,
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date")
                );
            }

            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return blog;
    }

    public static ArrayList<Blog> getBlogByUserId(Long userId) {
        ArrayList<Blog> blogs = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT blog.id, blog.user_id, blog.title, blog.content, blog.post_date, us.email, us.full_name" +
                            " FROM blogs blog INNER JOIN users us ON blog.user_id = us.id WHERE blog.user_id = ? ORDER BY blog.post_date DESC"
            );

            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Blog blog = new Blog();
                blog.setId(resultSet.getLong("id"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setPostDate(resultSet.getTimestamp("post_date"));
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setEmail(resultSet.getString("email"));
                user.setFullName(resultSet.getString("full_name"));
                blog.setUser(user);
                blogs.add(blog);
            }

            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return blogs;
    }

    public static boolean updateBlog(Blog blog) {
        int rows = 0;

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE blogs SET  user_id = ?, title = ?, content = ?, post_date = ? WHERE id = ?");

            preparedStatement.setLong(1, blog.getUser().getId());
            preparedStatement.setString(2, blog.getTitle());
            preparedStatement.setString(3, blog.getContent());
            preparedStatement.setTimestamp(4, Timestamp.from(Instant.now()));
            preparedStatement.setLong(5, blog.getId());

            rows = preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows > 0;
    }

    public static boolean deleteBlog(Long id) {
        int rows = 0;

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM blogs WHERE id = ?");

            preparedStatement.setLong(1, id);

            rows = preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows > 0;
    }

    public static boolean addComment(Comment comment) {
        int rows = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO comments (id, user_id, blog_id, comment, post_date) " +
                            "VALUES (NULL, ?, ?, ?, ?)"
            );

            preparedStatement.setLong(1, comment.getUser().getId());
            preparedStatement.setLong(2, comment.getBlog().getId());
            preparedStatement.setString(3, comment.getComment());
            preparedStatement.setTimestamp(4, Timestamp.from(Instant.now()));

            rows = preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static ArrayList<Comment> getAllComments(Long blogId) {
        ArrayList<Comment> comments = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT comment.id, comment.user_id, comment.blog_id, comment.comment, comment.post_date, us.full_name, us.email" +
                            " FROM comments comment INNER JOIN users us ON comment.user_id = us.id WHERE comment.blog_id = ?" +
                            " ORDER BY comment.post_date DESC "
            );

            preparedStatement.setLong(1, blogId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPostDate(resultSet.getTimestamp("post_date"));

                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                comment.setUser(user);

                Blog blog = new Blog();
                blog.setId(resultSet.getLong("blog_id"));
                comment.setBlog(blog);

                comments.add(comment);
            }

            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return comments;
    }

    public static ArrayList<Blog> getSearchedBlog(String title) {
        ArrayList<Blog> blogs = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT blog.id, blog.user_id, blog.title, blog.content, blog.post_date, us.email, us.full_name FROM blogs blog INNER JOIN users us ON blog.user_id = us.id WHERE blog.title LIKE ? "
            );

            preparedStatement.setString(1, "%"+title+"%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Blog blog = new Blog();
                blog.setId(resultSet.getLong("id"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setPostDate(resultSet.getTimestamp("post_date"));
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setEmail(resultSet.getString("email"));
                user.setFullName(resultSet.getString("full_name"));
                blog.setUser(user);
                blogs.add(blog);
            }

            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return blogs;
    }

}
