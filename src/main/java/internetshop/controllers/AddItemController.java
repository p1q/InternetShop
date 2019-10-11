package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.model.Item;
import internetshop.service.ItemService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class AddItemController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AddItemController.class);

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
        try {
            Item item = new Item(request.getParameter("name"),
                    Double.parseDouble(request.getParameter("price")));
            itemService.create(item);
            logger.info("Item was added.");
        } catch (NumberFormatException e) {
            logger.error(e);
        }

        response.sendRedirect(request.getContextPath() + "/show-all-items");
    }
}
