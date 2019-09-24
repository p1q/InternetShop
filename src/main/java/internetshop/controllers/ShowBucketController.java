package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.model.Item;
import internetshop.model.User;
import internetshop.service.BucketService;
import internetshop.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long userId = (Long) request.getSession(true).getAttribute("userId");
        User user = userService.get(userId);
        List<Item> items = bucketService.getAllItems(bucketService.get(user.getBucketId()));

        request.setAttribute("items", items);
        request.getRequestDispatcher("/WEB-INF/views/bucket.jsp")
                .forward(request, response);
    }
}
