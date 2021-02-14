package elldimi.spring.mobilelele.services.impls;

import elldimi.spring.mobilelele.models.entities.User;
import elldimi.spring.mobilelele.models.entities.UserRole;
import elldimi.spring.mobilelele.models.entities.enums.Role;
import elldimi.spring.mobilelele.repositories.UserRepository;
import elldimi.spring.mobilelele.security.CurrentUser;
import elldimi.spring.mobilelele.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository,
                           CurrentUser currentUser) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    @Override
    public boolean authenticate(String username, String password) {

        Optional<User> userOpt = userRepository.findUserByUsername(username);
        if (userOpt.isEmpty()){
            return false;
        }else {
            return passwordEncoder.matches(password, userOpt.get().getPassword());
        }
    }

    @Override
    public void loginUser(String userName) {

        User user = userRepository.findUserByUsername(userName).orElseThrow();

        List<Role> userRoles = user
                .getUserRoles()
                .stream()
                .map(UserRole::getRole)
                .collect(Collectors.toList());

        currentUser
                .setAnonymous(false)
                .setName(user.getUsername())
                .setUserRoles(userRoles);
    }

    @Override
    public void logoutUser() {
        currentUser.setAnonymous(true);
    }
}
