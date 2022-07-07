package kz.bitlab.servlets;

import kz.bitlab.models.Blog;
import kz.bitlab.models.Comment;
import kz.bitlab.db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(value = "/readBlog")
public class ReadBlogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Blog blog = DBManager.getBlog(Long.valueOf(id));

        ArrayList<Comment> comments = DBManager.getAllComments(Long.valueOf(id));
        request.setAttribute("comments", comments);
        request.setAttribute("blog", blog);
        request.getRequestDispatcher("readBlog.jsp").forward(request, response);
    }

}
