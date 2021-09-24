package edu.school21.cinema.servlet;

import edu.school21.cinema.model.User;
import org.apache.commons.io.FileUtils;
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
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

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
        byte[] file = FileUtils.readFileToByteArray(new File(imagesPath + user.getUser_id() + req.getPathInfo()));
        String encode = Base64.getEncoder().encodeToString(file);
        req.setAttribute("image", encode);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/image.jsp");
        dispatcher.forward(req, resp);
    }
}
