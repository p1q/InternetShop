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

public class IndexController extends HttpServlet {
    private static Logger LOGGER = Logger.getLogger((IndexController.class));

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Item item = itemService.get(1L);
        LOGGER.info(item);

        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }
}
