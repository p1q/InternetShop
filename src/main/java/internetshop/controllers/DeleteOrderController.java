package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.service.OrderService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        orderService.delete(Long.valueOf(request.getParameter("order_id")));
        response.sendRedirect(request.getContextPath() + "/show-all-orders");
    }
}
