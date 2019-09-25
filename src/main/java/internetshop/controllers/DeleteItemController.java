package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.service.ItemService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class DeleteItemController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AddItemController.class);

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            itemService.delete(Long.parseLong(request.getParameter("item_id")));
            logger.info("The item was deleted.");
        } catch (NumberFormatException e) {
            logger.error(e);
        }

        response.sendRedirect(request.getContextPath() + "/user/delete-item");
    }
}
