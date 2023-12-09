package com.bandiu.fileBr.servlets;




import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter("/*")
public class AuthenticationFilterServlet implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String path = httpRequest.getRequestURI();
        System.out.println(path);
        if (path.startsWith("/firstWeb/login")) {

            chain.doFilter(request, response);
            return;
        }
        if(session == null)
            System.out.println("ses = null");

        if (session == null || (String)session.getAttribute("username") == null) {
            httpResponse.sendRedirect("login");

        } else {
            chain.doFilter(request, response);
            System.out.println("Allow");
        }
    }


}
