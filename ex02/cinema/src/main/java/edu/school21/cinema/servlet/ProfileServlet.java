package edu.school21.cinema.servlet;

import edu.school21.cinema.model.User;
import edu.school21.cinema.service.UserService;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

@WebServlet("/profile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ProfileServlet extends HttpServlet {

    private String imagesPath;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext con = (ApplicationContext) context.getAttribute("springContext");
        imagesPath = (String) con.getBean("pathToImagesFolder");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("user", user);
        File imageDir = new File(imagesPath + user.getUser_id());
        if (!imageDir.exists()) {
            imageDir.mkdir();
        }
        req.setAttribute("images", imagesPath + user.getUser_id());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        File imageDir = new File(imagesPath + user.getUser_id());
        for (Part part : req.getParts()) {
            part.write(imageDir + File.separator + part.getSubmittedFileName());
        }
        req.setAttribute("images", imagesPath + user.getUser_id());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
        dispatcher.forward(req, resp);
    }
}
