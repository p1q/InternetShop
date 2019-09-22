package internetshop.model;

import internetshop.service.IdGenerator;
import java.util.ArrayList;
import java.util.List;

public class User {
    private Long userId;
    private String userName;
    private String surname;
    private String login;
    private String password;
    private String email;
    private String phone;
    private Bucket bucket;
    private List<Order> orders;

    public User() {
        this.userId = IdGenerator.generateUserId();
        orders = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public String toString() {
        return "User{"
                + "userId=" + userId
                + ", userName='" + userName + '\''
                + ", orders=" + orders
                + ", bucket=" + bucket
                + '}';
    }
}
