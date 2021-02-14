package elldimi.spring.mobilelele;

import elldimi.spring.mobilelele.models.entities.*;
import elldimi.spring.mobilelele.models.entities.enums.Category;
import elldimi.spring.mobilelele.models.entities.enums.Engine;
import elldimi.spring.mobilelele.models.entities.enums.Role;
import elldimi.spring.mobilelele.models.entities.enums.Transmission;
import elldimi.spring.mobilelele.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Component
public class DBInitializer implements CommandLineRunner {
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final OfferRepository offerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public DBInitializer(
            BrandRepository brandRepository,
            ModelRepository modelRepository,
            OfferRepository offerRepository,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            UserRoleRepository userRoleRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.offerRepository = offerRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        //TODO to do DB initialization from data.sql file!

        Brand ford = new Brand("Ford");
        Brand honda = new Brand("Honda");
        Brand yamaha = new Brand("Yamaha");

        brandRepository.saveAll(List.of(ford, honda, yamaha));

        Model fiesta = new Model(
                "Fiesta",
                Category.CAR,
                "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2017-ford-fiesta-1557785069.jpg?crop=0.817xw:1.00xh;0,0&resize=640:*",
                1976,
                ford);

        Model civic = new Model(
                "Civic",
                Category.CAR,
                "https://cars.usnews.com/static/images/Auto/custom/14461/2021_Honda_Civic_1.jpg",
                1972,
                honda);
        Model focus = new Model(
                "Focus",
                Category.CAR,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSw4J1GhssN62rwJMZ-KguprsA4EishMycdQ&usqp=CAU",
                1998,
                ford);
        Model YZFR3 = new Model(
                "YZF-R3",
                Category.MOTORCYCLE,
                "https://www.webbikeworld.com/wp-content/uploads/2020/01/2020-Yamaha-YZF-R3.jpg",
                2020,
                yamaha);

        modelRepository.saveAll(List.of(fiesta, civic, focus, YZFR3));

        createOffer(fiesta);

        initUser();
    }

    private void initUser() {
        UserRole adminRole = new UserRole().setRole(Role.ADMIN);
        UserRole userRole = new UserRole().setRole(Role.USER);

        userRoleRepository.saveAll(List.of(adminRole, userRole));

        User admin = new User();
        admin
                .setFirstName("Dandrick")
                .setLastName("Eldridge")
                .setUsername("DDE")
                .setPassword(passwordEncoder.encode("coward"))
                .setUserRoles(List.of(adminRole, userRole));

        User escapee = new User();
        escapee
                .setFirstName("Noname")
                .setLastName("Nobody")
                .setUsername("NNE")
                .setPassword(passwordEncoder.encode("craven"))
                .setUserRoles(List.of(userRole));

        userRepository.saveAll(List.of(admin, escapee));
    }

    private void createOffer(Model fiesta) {
        Offer fiestaOffer = new Offer();
        fiestaOffer
                .setEngine(Engine.GASOLINE)
                .setImageUrl("https://imgd.aeplcdn.com/1280x720/cw/cars/ford/fiesta.jpg?q=85")
                .setMileage(25000)
                .setPrice(BigDecimal.valueOf(10000))
                .setYear(2019)
                .setTransmission(Transmission.MANUAL)
                .setDescription("Driven by an old lady! Held in the garage during the winter season")
                .setModel(fiesta);

        offerRepository.save(fiestaOffer);
    }

}
