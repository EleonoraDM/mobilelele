package elldimi.spring.mobilelele.services.impls;

import elldimi.spring.mobilelele.models.entities.User;
import elldimi.spring.mobilelele.repositories.UserRepository;
import elldimi.spring.mobilelele.security.CurrentUser;
import elldimi.spring.mobilelele.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        currentUser
                .setAnonymous(false)
                .setName(userName);
    }
}
