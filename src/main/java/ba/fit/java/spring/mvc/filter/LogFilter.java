package ba.fit.java.spring.mvc.filter;

// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.util.*;

// Implements Filter class
@WebFilter(urlPatterns = {"/*"}, description = "Session Checker Filter")
public class LogFilter implements Filter  {
    private FilterConfig config = null;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        config.getServletContext().log("Initializing SessionCheckerFilter");
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        //
        // Check to see if user's session attribute contains an attribute
        // named AUTHENTICATED. If the attribute is not exists redirect
        // user to the login page.
        //
//        if (!request.getRequestURI().endsWith("login.jsp") &&
//                request.getSession().getAttribute("AUTHENTICATED") == null) {
//            response.sendRedirect(request.getContextPath() + "/login.jsp");
//        }
        String ipAddress = request.getRemoteAddr();

        // Log the IP address and current timestamp.
        System.out.println("IP "+ ipAddress + ", Time "+ new Date().toString());

        // Pass request back down the filter chain
        chain.doFilter(request,response);
    }

    public void destroy() {
        config.getServletContext().log("Destroying SessionCheckerFilter");
    }
}