package kz.bitlab.servlets;

import kz.bitlab.db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/deleteBlog")
public class DeleteBlogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String redirect = "";
        if (request.getSession().getAttribute("CURRENT_USER") != null) {
            if (DBManager.deleteBlog(Long.valueOf(id))) {
                redirect = "/home";
            }
        }else {
            redirect = "/login";
        }

        response.sendRedirect(redirect);
    }

}
