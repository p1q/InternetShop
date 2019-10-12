package internetshop.filters;

import static internetshop.model.Role.RoleName.ADMIN;
import static internetshop.model.Role.RoleName.USER;

import internetshop.annotations.Inject;
import internetshop.model.Role;
import internetshop.model.Role.RoleName;
import internetshop.model.User;
import internetshop.service.UserService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

public class AuthorizationFilter implements Filter {
    @Inject
    private static UserService userService;

    private Map<String, RoleName> protectedAdminUrls = new HashMap<>();
    private Map<String, RoleName> protectedUserUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedAdminUrls.put("/user/add-item", ADMIN);
        protectedAdminUrls.put("/user/delete-item", ADMIN);
        protectedAdminUrls.put("/user/delete-item-commit", ADMIN);
        protectedAdminUrls.put("/user/added-item", ADMIN);
        protectedAdminUrls.put("/user/delete-user", ADMIN);
        protectedAdminUrls.put("/user/show-all-users", ADMIN);
        protectedUserUrls.put("/user/show-all-orders", ADMIN);
        protectedUserUrls.put("/user/delete-order", ADMIN);

        protectedUserUrls.put("/user/add-item-to-bucket", USER);
        protectedUserUrls.put("/user/checkout", USER);
        protectedUserUrls.put("/user/delete-item-from-bucket", USER);
        protectedUserUrls.put("/user/show-bucket", USER);
        protectedUserUrls.put("/user/show-user-orders", USER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            processUnAuthenticated(request, response);
            return;
        }

        String requestedUrl = request.getRequestURI().replace(request.getContextPath(), "");
        Role.RoleName roleNameAdmin = protectedAdminUrls.get(requestedUrl);
        Role.RoleName roleNameUser = protectedUserUrls.get(requestedUrl);
        if (roleNameAdmin == null && roleNameUser == null) {
            processDenied(request, response);
            return;
        }

        String token = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("internetshop")) {
                token = cookie.getValue();
                break;
            }
        }

        if (token == null) {
            processUnAuthenticated(request, response);
        } else {
            Optional<User> user = userService.getByToken(token);
            if (user.isPresent()) {
                if (verifyRole(user.get(), roleNameAdmin) || verifyRole(user.get(), roleNameUser)) {
                    processAuthenticated(request, response, chain);
                } else {
                    processDenied(request, response);
                }
            } else {
                processUnAuthenticated(request, response);
            }
        }
    }

    private void processDenied(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/denied.jsp").forward(request, response);
    }

    private boolean verifyRole(User user, RoleName roleName) {
        return user.getRoles().stream().anyMatch(r -> r.getRoleName().equals(roleName));
    }

    private void processAuthenticated(HttpServletRequest request, HttpServletResponse response,
                                      FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    private void processUnAuthenticated(HttpServletRequest request,
                                        HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/login");
    }

    @Override
    public void destroy() {
    }
}
