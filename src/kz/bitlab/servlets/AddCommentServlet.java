package kz.bitlab.servlets;

import kz.bitlab.models.Blog;
import kz.bitlab.models.Comment;
import kz.bitlab.db.DBManager;
import kz.bitlab.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addComment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String comment = request.getParameter("commentText");
        String blogId = request.getParameter("blogId");
        String redirect = "/login";
        User user = (User) request.getSession().getAttribute("CURRENT_USER");
        if (user != null) {
            Comment c = new Comment();
            c.setComment(comment);
            c.setUser(user);
            Blog blog = new Blog();
            blog.setId(Long.valueOf(blogId));
            c.setBlog(blog);
            boolean temp = DBManager.addComment(c);
            if (temp) {
               redirect = "/readBlog?id="+blogId;
            }
        }

        response.sendRedirect(redirect);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
