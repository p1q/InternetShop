package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.model.Bucket;
import internetshop.model.User;
import internetshop.service.BucketService;
import internetshop.service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class AddUserController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(InjectInitializer.class);
    @Inject
    private static UserService userService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/add-user.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        User user = new User();
        userService.create(user);
        user.setUserName(request.getParameter("name"));
        Bucket bucket = new Bucket();
        bucketService.create(bucket);
        user.setBucketId(bucket.getBucketId());

        try (PrintWriter writer = response.getWriter()) {
            writer.println("<h3>USER ADDED.</h3>");
            logger.info("A user was added.");
        }
    }
}
