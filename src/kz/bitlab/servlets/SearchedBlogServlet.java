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

@WebServlet(value = "/searchedBlog")
public class SearchedBlogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        ArrayList<Blog> blogs = DBManager.getSearchedBlog(search);

        request.setAttribute("blogsSearched", blogs);
        request.getRequestDispatcher("searchedBlog.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
