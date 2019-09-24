package internetshop.controllers;

import internetshop.annotations.Inject;
import internetshop.model.Bucket;
import internetshop.model.Role;
import internetshop.model.User;
import internetshop.service.BucketService;
import internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    @Inject
    private static UserService userService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        User user = new User();
        user.setUserName("Victor");
        user.setSurname("Karah");
        user.addRole(Role.of("USER"));
        user.setLogin("user");
        user.setPassword("1");
        userService.create(user);
        Bucket bucketForUser = new Bucket();
        bucketService.create(bucketForUser);
        user.setBucketId(bucketForUser.getBucketId());

        User admin = new User();
        admin.setUserName("Mykola");
        admin.setSurname("Pashcenko");
        admin.addRole(Role.of("ADMIN"));
        admin.setLogin("admin");
        admin.setPassword("1");
        userService.create(admin);
        Bucket bucketForAdmin = new Bucket();
        bucketService.create(bucketForAdmin);
        admin.setBucketId(bucketForAdmin.getBucketId());

        response.sendRedirect(request.getContextPath() + "/");
    }
}
