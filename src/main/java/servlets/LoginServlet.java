package servlets;


import view.LoginForm;
import model.UserService;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if (UserService.isValidUser(username,password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            System.out.println(session.getAttribute("username"));
            response.sendRedirect("index");
        } else {
            response.sendRedirect("login");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setCharacterEncoding("UTF-8");
        Writer writer = response.getWriter();
        writer.write(LoginForm.getContent());
    }

}