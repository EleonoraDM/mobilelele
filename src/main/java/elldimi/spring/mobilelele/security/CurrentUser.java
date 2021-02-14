package elldimi.spring.mobilelele.security;

import elldimi.spring.mobilelele.models.entities.enums.Role;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class CurrentUser {

    private static final String ANONYMOUS = "anonymous";
    private String name = ANONYMOUS;
    private boolean isAnonymous = true;
    private List<Role> userRoles = new ArrayList<>();

    public CurrentUser setUserRoles(List<Role> roles) {
        userRoles.clear();
        userRoles.addAll(roles);
        return this;
    }

    public String getName() {
        return name;
    }

    public CurrentUser setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public CurrentUser setAnonymous(boolean anonymous) {
        if (anonymous) {
            this.name = ANONYMOUS;
            this.userRoles.clear();
        }
        isAnonymous = anonymous;
        return this;
    }

    public boolean isLoggedIn() {
        return !isAnonymous();
    }

    public boolean isAdmin(){
        return userRoles.contains(Role.ADMIN);
    }
}
