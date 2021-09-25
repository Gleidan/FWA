package edu.school21.cinema.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (uri.startsWith("/FWA/signIn") || uri.startsWith("/FWA/signUp")) {
            if (session.getAttribute("user") != null) {
                ((HttpServletResponse) servletResponse).sendRedirect("/FWA/profile");
                return;
            }
        } else {
            if (session.getAttribute("user") == null) {
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
