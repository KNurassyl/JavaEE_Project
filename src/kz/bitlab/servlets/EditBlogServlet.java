package kz.bitlab.servlets;

import kz.bitlab.models.Blog;
import kz.bitlab.db.DBManager;
import kz.bitlab.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/editBlog")
public class EditBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("CURRENT_USER") != null) {
            String id = request.getParameter("id");
            request.setAttribute("blog", DBManager.getBlog(Long.valueOf(id)));
            request.getRequestDispatcher("editBlog.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/login";
        request.setCharacterEncoding("utf8");
        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
        if (currentUser != null) {
            String title = request.getParameter("titleUpdated");
            String content = request.getParameter("contentUpdated");
            String id = request.getParameter("id");
            Blog blog = new Blog();
            blog.setId(Long.valueOf(id));
            blog.setTitle(title);
            blog.setContent(content);
            blog.setUser(currentUser);
            if (DBManager.updateBlog(blog)) {
                redirect = "/home";
            }
        }

        response.sendRedirect(redirect);

    }
}
