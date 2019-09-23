package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userService.delete(Long.valueOf(request.getParameter("user_id")));
        response.sendRedirect(request.getContextPath() + "/show-all-users");
    }
}
