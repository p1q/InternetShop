package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.model.Order;
import internetshop.service.OrderService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowUserOrdersController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long userId = (Long) request.getSession(true).getAttribute("userId");
        List<Order> allOrders = new ArrayList<>(orderService.getAll());
        List<Order> orders = new ArrayList<>();

        for (Order i : allOrders) {
            if (i.getUser().getUserId().equals(userId)) {
                orders.add(i);
            }
        }

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/WEB-INF/views/show-user-orders.jsp")
                .forward(request, response);
    }
}
