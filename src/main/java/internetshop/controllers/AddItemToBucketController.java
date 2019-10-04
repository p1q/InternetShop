package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.model.Bucket;
import internetshop.model.Item;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import java.io.IOException;
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
        Item itemToAdd = itemService.get(Long.valueOf(request.getParameter("item_id")));
        Bucket bucket = bucketService.getByUserId(userId);
        bucketService.addItem(bucket, itemToAdd);

        response.sendRedirect(request.getContextPath() + "/user/show-bucket");
    }
}
