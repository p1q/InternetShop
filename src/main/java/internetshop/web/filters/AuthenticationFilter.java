package internetshop.web.filters;

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
import org.apache.log4j.Logger;

public class AuthenticationFilter implements Filter {
    private static Logger logger = Logger.getLogger(AuthenticationFilter.class);
    @Inject
    private static UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("internetshop")) {
                Optional<User> user = userService.getByToken(cookie.getValue());
                if (user.isPresent()) {
                    logger.info("User " + user.get().getLogin()
                            + " was authenticated successfully.");
                } else {
                    logger.info("User wasn't authenticated!");
                }
            }
        }
    }

    @Override
    public void destroy() {
    }
}
