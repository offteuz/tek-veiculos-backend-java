package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.model;

import br.com.teksystem.tekveiculos.vehicle.domain.brand.exception.BrandNotFoundException;
import br.com.teksystem.tekveiculos.vehicle.domain.model.Model;
import br.com.teksystem.tekveiculos.vehicle.domain.model.exception.ModelNotFoundException;
import br.com.teksystem.tekveiculos.vehicle.domain.model.port.ModelRepository;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.brand.BrandJpaEntity;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.brand.SpringDataBrandRepository;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.model.mapper.ModelJpaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ModelPostgresAdapter implements ModelRepository {
    private final ModelJpaMapper modelJpaMapper;
    private final SpringDataModelRepository springDataModelRepository;
    private final SpringDataBrandRepository springDataBrandRepository;

    public ModelPostgresAdapter(
            ModelJpaMapper modelJpaMapper,
            SpringDataModelRepository springDataModelRepository,
            SpringDataBrandRepository springDataBrandRepository
    ) {
        this.modelJpaMapper = modelJpaMapper;
        this.springDataModelRepository = springDataModelRepository;
        this.springDataBrandRepository = springDataBrandRepository;
    }

    @Override
    public Model save(Model model) {
        BrandJpaEntity brandJpaEntity = springDataBrandRepository.findById(model.brandId())
                .orElseThrow(() -> new BrandNotFoundException(model.brandId()));

        ModelJpaEntity modelJpaEntity = modelJpaMapper.toEntity(model);
        modelJpaEntity.setBrand(brandJpaEntity);

        ModelJpaEntity modelJpaEntitySaved = springDataModelRepository.save(modelJpaEntity);

        return modelJpaMapper.toModel(modelJpaEntitySaved);
    }

    @Override
    public List<Model> findAll() {
        return springDataModelRepository.findAll()
                .stream()
                .map(modelJpaMapper::toModel)
                .toList();
    }

    @Override
    public Optional<Model> findById(Long id) {
        return springDataModelRepository.findById(id).map(modelJpaMapper::toModel);
    }

    @Override
    public boolean existsById(Long id) {
        return springDataModelRepository.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return springDataModelRepository.existsByName(name);
    }

    @Override
    public boolean existsByNameAndBrandId(String name, Long brandId) {
        return springDataModelRepository.existsByNameAndBrandId(name, brandId);
    }

    @Override
    public Model update(Model model) {
        ModelJpaEntity modelJpaEntity = springDataModelRepository.findById(model.id())
                .orElseThrow(() -> new ModelNotFoundException(model.id()));

        modelJpaMapper.updateEntityFromModel(model, modelJpaEntity);

        if (model.brandId() == null) {
            BrandJpaEntity brand = springDataBrandRepository.findById(model.brandId())
                    .orElseThrow(() -> new BrandNotFoundException(model.brandId()));

            modelJpaEntity.setBrand(brand);
        }

        ModelJpaEntity modelJpaEntitySaved = springDataModelRepository.save(modelJpaEntity);

        return modelJpaMapper.toModel(modelJpaEntitySaved);
    }

    @Override
    public void deleteById(Long id) {
        springDataModelRepository.deleteById(id);
    }
}
