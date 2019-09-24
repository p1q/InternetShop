package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.service.BucketService;
import internetshop.service.OrderService;
import internetshop.service.UserService;
import java.io.IOException;
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
        Long bucketId = userService.get(userId).getBucketId();
        orderService.checkout(bucketId, userId);
        bucketService.clear(bucketService.get(bucketId));

        response.sendRedirect(request.getContextPath() + "/user/show-all-orders");
    }
}
