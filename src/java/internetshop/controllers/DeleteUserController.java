package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.model.User;
import internetshop.service.UserService;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Optional<User> user = userService.get(Long.valueOf(request.getParameter("user_id")));
        user.ifPresent(value -> userService.delete(value));

        response.sendRedirect(request.getContextPath() + "/user/show-all-users");
    }
}
