package elldimi.spring.mobilelele.services;

public interface UserService {

    boolean authenticate(String username, String password);

    void loginUser(String userName);

    void logoutUser();
}
