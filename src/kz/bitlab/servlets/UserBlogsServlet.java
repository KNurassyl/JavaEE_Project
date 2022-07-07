package kz.bitlab.servlets;

import kz.bitlab.db.DBManager;
import kz.bitlab.models.Blog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(value = "/userBlogs")
public class UserBlogsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ArrayList<Blog> blogArrayList = DBManager.getBlogByUserId(Long.valueOf(id));
        request.setAttribute("blogs", blogArrayList);
        request.getRequestDispatcher("userBlogs.jsp").forward(request, response);
    }

}
