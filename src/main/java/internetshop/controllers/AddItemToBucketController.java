package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.model.Bucket;
import internetshop.model.Item;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddItemToBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long userId = (Long) request.getSession(true).getAttribute("userId");
        Optional<Item> itemToAdd = itemService.get(Long.valueOf(request.getParameter("item_id")));
        if (itemToAdd.isPresent()) {
            Optional<Bucket> bucket = bucketService.getByUserId(userId);
            bucket.ifPresent(value -> bucketService.addItem(value, itemToAdd.get()));
        }

        response.sendRedirect(request.getContextPath() + "/user/show-bucket");
    }
}
