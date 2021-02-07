package elldimi.spring.mobilelele.models;

public class UserCredentialsContainer {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public UserCredentialsContainer setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserCredentialsContainer setPassword(String password) {
        this.password = password;
        return this;
    }
}
