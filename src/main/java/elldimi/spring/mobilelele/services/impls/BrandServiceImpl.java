package elldimi.spring.mobilelele.services.impls;

import elldimi.spring.mobilelele.models.entities.Brand;
import elldimi.spring.mobilelele.models.entities.Model;
import elldimi.spring.mobilelele.models.views.BrandViewModel;
import elldimi.spring.mobilelele.models.views.ModelViewModel;
import elldimi.spring.mobilelele.repositories.ModelRepository;
import elldimi.spring.mobilelele.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;


    public BrandServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BrandViewModel> getAllBrands() {

        List<BrandViewModel> result = new ArrayList<>();
        List<Model> allModels =
                this.modelRepository.findAll();

        allModels.forEach(m -> {
            Brand brand = m.getBrand();
            Optional<BrandViewModel> brandModel = findByName(brand.getName(), result);

            if (brandModel.isEmpty()) {
                BrandViewModel newModel = new BrandViewModel();
                modelMapper.map(brand, newModel);
                result.add(newModel);
                brandModel = Optional.of(newModel);
            }

            ModelViewModel newModel = new ModelViewModel();
            modelMapper.map(m, newModel);
            brandModel.get().addModel(newModel);
        });

        return result;
    }

    private static Optional<BrandViewModel> findByName(String name, List<BrandViewModel> brands) {
        return brands.
                stream().
                filter(b -> b.getName().equals(name)).
                findAny();
    }
}
