package kz.bitlab.servlets;

import kz.bitlab.models.Blog;
import kz.bitlab.db.DBManager;
import kz.bitlab.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addBlog")
public class AddBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("CURRENT_USER") != null) {
            request.getRequestDispatcher("addBlog.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/login";
        request.setCharacterEncoding("utf8");
        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
        if (currentUser != null) {
            redirect = "/addBlog?error";
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            Blog blog = new Blog();
            blog.setTitle(title);
            blog.setContent(content);
            blog.setUser(currentUser);
            if (DBManager.addBlog(blog)) {
                redirect = "/addBlog?success";
            }
        }

        response.sendRedirect(redirect);

    }
}
