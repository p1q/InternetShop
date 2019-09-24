package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.model.Bucket;
import internetshop.model.User;
import internetshop.service.BucketService;
import internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationController extends HttpServlet {

    @Inject
    private static UserService userService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/register.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setStatus(200);
        response.setContentType("text/html");

        User user = new User();
        user.setUserName(request.getParameter("username"));
        user.setSurname((request.getParameter("surname")));
        user.setEmail((request.getParameter("email")));
        user.setPhone((request.getParameter("phone")));
        user.setLogin((request.getParameter("login")));
        user.setPassword((request.getParameter("password")));
        userService.create(user);
        Bucket bucket = new Bucket(user);
        bucketService.create(bucket);

        response.sendRedirect(request.getContextPath() + "/show-all-users");
    }
}