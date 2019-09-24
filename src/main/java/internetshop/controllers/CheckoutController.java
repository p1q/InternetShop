package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.service.BucketService;
import internetshop.service.OrderService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckoutController extends HttpServlet {
    private static final Long BUCKET_ID = 0L;

    @Inject
    private static BucketService bucketService;

    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        orderService.checkout(bucketService.get(BUCKET_ID));
        bucketService.clear(bucketService.get(BUCKET_ID));

        response.sendRedirect(request.getContextPath() + "/show-all-orders");
    }
}
