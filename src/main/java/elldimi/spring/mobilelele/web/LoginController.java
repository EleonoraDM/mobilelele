package elldimi.spring.mobilelele.web;

import elldimi.spring.mobilelele.models.UserCredentialsContainer;
import elldimi.spring.mobilelele.security.CurrentUser;
import elldimi.spring.mobilelele.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String showLogin() {
        //TODO Implementation
        return "auth-login";
    }

    @PostMapping("/users/login")
    public String login(UserCredentialsContainer model) {
        if (userService.authenticate(model.getUsername(), model.getPassword())) {
            userService.loginUser(model.getUsername());
            return "redirect:/";
        } else {
            return "redirect:/users/login";
        }
    }

    @PostMapping("/users/logout")
    public String logout() {
        userService.logoutUser();
        return "redirect:/";


    }
}
