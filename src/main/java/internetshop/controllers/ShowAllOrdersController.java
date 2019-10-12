package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.service.OrderService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAllOrdersController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List orders = orderService.getAll();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/WEB-INF/views/show-all-orders.jsp")
                .forward(request, response);
    }
}
