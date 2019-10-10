package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.model.Bucket;
import internetshop.model.Item;
import internetshop.service.BucketService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long userId = (Long) request.getSession(true).getAttribute("userId");
        Optional<Bucket> bucket = bucketService.getByUserId(userId);
        if (bucket.isPresent()) {
            List<Item> items = bucketService.getAllItems(bucket.get());
            request.setAttribute("items", items);
        }

        request.getRequestDispatcher("/WEB-INF/views/bucket.jsp")
                .forward(request, response);
    }
}
