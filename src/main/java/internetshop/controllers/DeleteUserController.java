package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.model.User;
import internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        User user = userService.get(Long.valueOf(request.getParameter("user_id")));
        userService.delete(user);
        response.sendRedirect(request.getContextPath() + "/user/show-all-users");
    }
}
