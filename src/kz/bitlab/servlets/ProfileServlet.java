package kz.bitlab.servlets;

import kz.bitlab.db.DBManager;
import kz.bitlab.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("CURRENT_USER");
        if (user != null) {
            request.setAttribute("blogs", DBManager.getBlogByUserId(user.getId()));
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
