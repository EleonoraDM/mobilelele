package elldimi.spring.mobilelele.models.entities;

import elldimi.spring.mobilelele.models.entities.enums.Engine;
import elldimi.spring.mobilelele.models.entities.enums.Transmission;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    @Column(length = 500)
    private String description;
    @Enumerated(EnumType.STRING)
    private Engine engine;
    @Column(length = 512, name = "image_url")
    private String imageUrl;
    private int mileage;
    private BigDecimal price;
    private int year;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    @ManyToOne
    private Model model;
    @ManyToOne
    private User seller;

    public User getSeller() {
        return seller;
    }

    public Offer setSeller(User seller) {
        this.seller = seller;
        return this;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public Offer setTransmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Offer setDescription(String description) {
        this.description = description;
        return this;
    }

    public Engine getEngine() {
        return engine;
    }

    public Offer setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Offer setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getMileage() {
        return mileage;
    }

    public Offer setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Offer setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Offer setYear(int year) {
        this.year = year;
        return this;
    }

    public Model getModel() {
        return model;
    }

    public Offer setModel(Model model) {
        this.model = model;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Offer{");
        sb.append("description='").append(description).append('\'');
        sb.append(", engine=").append(engine);
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append(", mileage=").append(mileage);
        sb.append(", price=").append(price);
        sb.append(", year=").append(year);
        sb.append(", transmission=").append(transmission);
        sb.append(", model=").append(model);
        sb.append(", seller=").append(seller);
        sb.append('}');
        return sb.toString();
    }
}
