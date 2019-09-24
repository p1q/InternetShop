package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.model.User;
import internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class LoginController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LoginController.class);
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("loginValue", "");
        request.setAttribute("passwordValue", "");
        request.getRequestDispatcher("/WEB-INF/views/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            User user = userService.login(login, password);
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", user.getUserId());
            Cookie cookie = new Cookie("internetshop", user.getToken());
            response.addCookie(cookie);
            logger.info("Successful login by user" + user);
            response.sendRedirect(request.getContextPath() + "/");
        } catch (internetshop.exceptions.AuthenticationException e) {
            request.setAttribute("errorMsg", "Invalid login or password!");
            logger.error(e);
            request.setAttribute("loginValue", login);
            request.setAttribute("passwordValue", password);
            request.getRequestDispatcher("/WEB-INF/views/login.jsp")
                    .forward(request, response);
        }
    }
}
