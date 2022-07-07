package kz.bitlab.servlets;

import kz.bitlab.db.DBManager;
import kz.bitlab.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String redirect = "/login?email_error";

        User user = DBManager.getUser(email);

        if(user != null) {
            if (user.getPassword().equals(password)) {
                 redirect = "/profile";
                 request.getSession().setAttribute("CURRENT_USER", user);
            }
            else {
                redirect = "/login?password_error";
            }
        }

        response.sendRedirect(redirect);
    }
}
