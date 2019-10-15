package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.model.Item;
import internetshop.service.ItemService;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class DeleteItemController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AddItemController.class);

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            Optional<Item> item = itemService.get(Long.parseLong(request.getParameter("item_id")));
            if (item.isPresent()) {
                itemService.delete(item.get());
                LOGGER.info("The item was deleted.");
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }

        response.sendRedirect(request.getContextPath() + "/user/delete-item");
    }
}
