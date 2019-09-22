package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.model.Item;
import internetshop.service.ItemService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddItemController extends HttpServlet {
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/add-item.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setStatus(200);
        response.setContentType("text/html");

        try {
            Item item = new Item(request.getParameter("name"),
                    Double.parseDouble(request.getParameter("price")));
            itemService.create(item);
        } catch (NumberFormatException e) {
            try (PrintWriter writer = response.getWriter()) {
                writer.println("<h3>ITEM ADDING ERROR!</h3>");
                writer.println("<h4>Invalid price format.</h4>");
            }
        }

        try (PrintWriter writer = response.getWriter()) {
            writer.println("<h3>ADDED ITEM:</h3>");
            writer.println("<h4>Item Name: " + request.getParameter("name") + "</h4>");
            writer.println("<h4>Item Price: " + request.getParameter("price") + "</h4>");
            writer.println("<h4>Catalog Section: " + request.getParameter("section") + "</h4>");
        }
    }
}
