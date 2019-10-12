package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.model.Bucket;
import internetshop.model.User;
import internetshop.service.BucketService;
import internetshop.service.OrderService;
import internetshop.service.UserService;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckoutController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static OrderService orderService;
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long userId = (Long) request.getSession(true).getAttribute("userId");
        Optional<Bucket> bucket = bucketService.getByUserId(userId);
        Optional<User> user = userService.get(userId);
        if (bucket.isPresent() && user.isPresent()) {
            orderService.checkout(bucket.get(), user.get());
            bucketService.clear(bucket.get().getBucketId());
        }

        response.sendRedirect(request.getContextPath() + "/user/show-user-orders");
    }
}
