package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.exceptions.LoginAlreadyExistsException;
import internetshop.exceptions.PasswordsDontMatchException;
import internetshop.model.User;
import internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class RegistrationController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/register.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
            try {
                if (userService.isLoginExists(request.getParameter("login"))) {
                    throw new LoginAlreadyExistsException("Login already exists!");
                }
                if (!(request.getParameter("password"))
                        .equals((request.getParameter("password-repeat")))) {
                    throw new PasswordsDontMatchException("Passwords don't match!");
                }
            } catch (LoginAlreadyExistsException e) {
                request.setAttribute("errorMsg", "Login already exists!");
                LOGGER.error(e);
                request.getRequestDispatcher("/WEB-INF/views/register.jsp")
                        .forward(request, response);
                return;
            } catch (PasswordsDontMatchException e) {
                request.setAttribute("errorMsg", "Passwords don't match!");
                LOGGER.error(e);
                request.getRequestDispatcher("/WEB-INF/views/register.jsp")
                        .forward(request, response);
                return;
            }

        User user = new User();
        user.setUserName(request.getParameter("username"));
        user.setSurname((request.getParameter("surname")));
        user.setEmail((request.getParameter("email")));
        user.setPhone((request.getParameter("phone")));
        user.setLogin((request.getParameter("login")));
        user.setPassword((request.getParameter("password")));
        user.setPassword((request.getParameter("password-repeat")));
        userService.create(user);

        request.setAttribute("registrationFinished", "Registration completed successfully!");
        request.getRequestDispatcher("/WEB-INF/views/index.jsp")
                .forward(request, response);
    }
}
