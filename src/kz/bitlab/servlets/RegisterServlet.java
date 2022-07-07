package kz.bitlab.servlets;

import kz.bitlab.db.DBManager;
import kz.bitlab.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String re_password = request.getParameter("re_password");
        String full_name = request.getParameter("full_name");

        User user = null;
        User check = DBManager.getUser(email);

        String redirect = "";

        if (check != null) {
            if (check.getPassword().equals(password) && check.getFullName().equals(full_name)) {
                redirect = "/register?user_exist";
            }
            else {
                redirect = "/register?email_exist";
            }
        }else {
            if (re_password.equals(password)) {
                user = new User(null, email, password, full_name);
                if (DBManager.addUser(user)) {
                    redirect = "/login";
                }
            }
            else {
                redirect = "/register?password_isNotSame";
            }
        }

        response.sendRedirect(redirect);
    }
}
