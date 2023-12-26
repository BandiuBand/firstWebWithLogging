package servlets;

import model.UserService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/verification")
public class VerificationServlet extends HttpServlet {
    private static final String successful = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>Verification Successful</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #e9f5f5;
                        text-align: center;
                        padding-top: 50px;
                    }
                    .message {
                        color: #4CAF50;
                        font-size: 24px;
                    }
                </style>
            </head>
            <body>
                <div class="message">
                    Ваш обліковий запис успішно підтверджено!
                </div>
                <div>
                <a href="/firstWeb/index">Додому</a>
                </div>
            </body>
            </html>""";
    private static final String unsuccessful = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>Verification Failed</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #fde4e1;
                        text-align: center;
                        padding-top: 50px;
                    }
                    .message {
                        color: #D32F2F;
                        font-size: 24px;
                    }
                </style>
            </head>
            <body>
                <div class="message">
                    На жаль, ми не можемо підтвердити ваш обліковий запис. Спробуйте ще раз.
                </div>
                <div>
                <a href="/firstWeb/index">Додому</a>
                </div>
            </body>
            </html>""";
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String verToken = request.getParameter("verificationtoken");
        String username = request.getParameter("username");

        PrintWriter writer = response.getWriter();
        String responseText="";
        if (UserService.verificationUser(username,verToken))
            responseText = successful;
        else
            responseText = unsuccessful;
        System.out.println(responseText);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        writer.write(responseText);

    }
}

