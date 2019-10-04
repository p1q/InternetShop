package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.service.BucketService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteItemFromBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long userId = (Long) request.getSession(true).getAttribute("userId");
        Long itemToDeleteId = Long.valueOf(request.getParameter("item_id"));
        Long bucketId = bucketService.getByUserId(userId).getBucketId();
        bucketService.deleteItem(bucketId, itemToDeleteId);

        response.sendRedirect(request.getContextPath() + "/user/show-bucket");
    }
}
