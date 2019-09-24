package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteItemFromBucketController extends HttpServlet {
    private static final Long BUCKET_ID = 0L;

    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        bucketService.deleteItem(bucketService.get(BUCKET_ID),
                itemService.get(Long.valueOf(request.getParameter("item_id"))));
        response.sendRedirect(request.getContextPath() + "/show-bucket");
    }
}
