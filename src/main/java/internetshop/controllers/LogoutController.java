package internetshop.controllers;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute("userId", "");

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("internetshop")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        response.sendRedirect(request.getContextPath() + "/");
    }
}
