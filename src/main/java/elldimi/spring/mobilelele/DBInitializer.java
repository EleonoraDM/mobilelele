package elldimi.spring.mobilelele;

import elldimi.spring.mobilelele.models.entities.Brand;
import elldimi.spring.mobilelele.models.entities.Model;
import elldimi.spring.mobilelele.models.entities.Offer;
import elldimi.spring.mobilelele.models.entities.User;
import elldimi.spring.mobilelele.models.entities.enums.Category;
import elldimi.spring.mobilelele.models.entities.enums.Engine;
import elldimi.spring.mobilelele.models.entities.enums.Transmission;
import elldimi.spring.mobilelele.repositories.BrandRepository;
import elldimi.spring.mobilelele.repositories.ModelRepository;
import elldimi.spring.mobilelele.repositories.OfferRepository;
import elldimi.spring.mobilelele.repositories.UserRepository;
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

    public DBInitializer(
            BrandRepository brandRepository,
            ModelRepository modelRepository,
            OfferRepository offerRepository,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.offerRepository = offerRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
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

        initAdmin();

    }

    private void initAdmin() {
        User admin = new User();
        admin
                .setFirstName("Dandrick")
                .setLastName("Eldridge")
                .setUsername("DDE")
                .setPassword(passwordEncoder.encode("coward"));
        userRepository.save(admin);
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
