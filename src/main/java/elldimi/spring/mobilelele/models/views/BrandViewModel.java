package elldimi.spring.mobilelele.models.views;

import java.util.ArrayList;
import java.util.List;

public class BrandViewModel {
    private String name;
    private List<ModelViewModel> models = new ArrayList<>();

    public BrandViewModel addModel(ModelViewModel modelViewModel) {
        this.models.add(modelViewModel);
        return this;
    }

    public String getName() {
        return name;
    }

    public BrandViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public List<ModelViewModel> getModels() {
        return models;
    }

    public BrandViewModel setModels(List<ModelViewModel> models) {
        this.models = models;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BrandViewModel{");
        sb.append("name='").append(name).append('\'');
        sb.append(", models=").append(models);
        sb.append('}');
        return sb.toString();
    }
}
