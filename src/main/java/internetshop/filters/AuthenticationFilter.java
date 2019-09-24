package internetshop.filters;

import internetshop.annotations.Inject;
import internetshop.model.User;
import internetshop.service.UserService;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {
    @Inject
    private static UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getCookies() == null) {
            processUnAuthenticated(request, response);
            return;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("internetshop")) {
                Optional<User> user = userService.getByToken(cookie.getValue());
                if (user.isPresent()) {
                    chain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
        }
        processUnAuthenticated(request, response);
    }

    private void processUnAuthenticated(HttpServletRequest request,
                                        HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/login");
    }

    @Override
    public void destroy() {
    }
}
