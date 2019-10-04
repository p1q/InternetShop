package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.service.BucketService;
import internetshop.service.OrderService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckoutController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long userId = (Long) request.getSession(true).getAttribute("userId");
        Long bucketId = bucketService.getByUserId(userId).getBucketId();
        orderService.checkout(bucketId, userId);
        bucketService.clear(bucketId);

        response.sendRedirect(request.getContextPath() + "/user/show-all-orders");
    }
}
