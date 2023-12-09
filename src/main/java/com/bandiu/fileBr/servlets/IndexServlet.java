package com.bandiu.fileBr.servlets;
import view.HtmlContentMaker;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {


        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String root = HtmlContentMaker.getRoot();
            String servletPath = request.getContextPath() + "/dir";
            String fullPath = servletPath + "?path=/";
            response.setCharacterEncoding("UTF-8");
            response.sendRedirect(fullPath);
        }

}
