package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.model.Item;
import internetshop.service.BucketService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowBucketController extends HttpServlet {
    private static final Long BUCKET_ID = 0L;

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Item> items = bucketService.getAllItems(bucketService.get(BUCKET_ID));
        request.setAttribute("items", items);
        request.getRequestDispatcher("/WEB-INF/views/bucket.jsp")
                .forward(request, response);
    }
}
