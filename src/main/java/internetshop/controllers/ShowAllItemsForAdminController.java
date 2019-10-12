package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.model.Item;
import internetshop.service.ItemService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAllItemsForAdminController extends HttpServlet {
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Item> items = itemService.getAll();
        request.setAttribute("items", items);
        request.getRequestDispatcher("/WEB-INF/views/delete-item.jsp").forward(request, response);
    }
}
