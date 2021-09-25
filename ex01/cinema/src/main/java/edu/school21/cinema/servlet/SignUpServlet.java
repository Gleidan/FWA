package edu.school21.cinema.servlet;

import edu.school21.cinema.model.User;
import edu.school21.cinema.service.AuthenticationService;
import edu.school21.cinema.service.UserService;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private UserService userService;
    private AuthenticationService authenticationService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext con = (ApplicationContext) context.getAttribute("springContext");
        userService = con.getBean(UserService.class);
        authenticationService = con.getBean(AuthenticationService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/jsp/signUp.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            User user = new User(null, email, password, firstName, lastName);
            int id = userService.save(user);
            user.setUser_id(id);
            HttpSession session = req.getSession();
            authenticationService.save(req.getRemoteAddr(), id);
            session.setAttribute("user", user);
            resp.sendRedirect("/FWA/profile");
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp");
            req.setAttribute("error", e.getMessage());
            dispatcher.forward(req, resp);
        }
    }
}
