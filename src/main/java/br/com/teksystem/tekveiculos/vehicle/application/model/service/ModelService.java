package br.com.teksystem.tekveiculos.vehicle.application.model.service;

import br.com.teksystem.tekveiculos.vehicle.application.model.dto.input.CreateModelCommand;
import br.com.teksystem.tekveiculos.vehicle.application.model.dto.input.UpdateModelCommand;
import br.com.teksystem.tekveiculos.vehicle.application.model.dto.output.ModelResponse;
import br.com.teksystem.tekveiculos.vehicle.application.model.mapper.ModelMapper;
import br.com.teksystem.tekveiculos.vehicle.domain.brand.Brand;
import br.com.teksystem.tekveiculos.vehicle.domain.brand.exception.BrandNotFoundException;
import br.com.teksystem.tekveiculos.vehicle.domain.brand.port.BrandRepository;
import br.com.teksystem.tekveiculos.vehicle.domain.model.Model;
import br.com.teksystem.tekveiculos.vehicle.domain.model.exception.ModelNotFoundException;
import br.com.teksystem.tekveiculos.vehicle.domain.model.port.ModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ModelService {
    public final ModelRepository modelRepository;
    public final BrandRepository brandRepository;
    public final ModelMapper modelMapper;

    public ModelService(ModelRepository modelRepository, BrandRepository brandRepository, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public ModelResponse save(CreateModelCommand createModelCommand) {
        Brand brand = brandRepository.findById(createModelCommand.brandId())
                .orElseThrow(() -> new BrandNotFoundException(createModelCommand.brandId()));

        Model model = modelMapper.toDomain(createModelCommand);

        if (modelRepository.existsByNameAndBrandId(model.name(), model.brandId())) {
            throw new IllegalArgumentException("A model with this name already exists for this brand");
        }

        Model modelSaved = modelRepository.save(model);

        return modelMapper.toResponse(modelSaved, brand);
    }

    public List<ModelResponse> findAll() {
        List<Model> models = modelRepository.findAll();

        Set<Long> brandsId = models.stream()
                .map(Model::brandId)
                .collect(Collectors.toSet());

        Map<Long, Brand> brandMap = brandRepository.findAllById(brandsId)
                .stream()
                .collect(Collectors.toMap(Brand::id, Function.identity()));

        return models.stream()
                .map(model -> modelMapper.toResponse(model, brandMap.get(model.brandId())))
                .toList();
    }

    public ModelResponse findById(Long id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(id));

        Brand brand = brandRepository.findById(model.brandId())
                .orElseThrow(() -> new BrandNotFoundException(model.brandId()));

        return modelMapper.toResponse(model, brand);
    }

    @Transactional
    public ModelResponse update(Long id, UpdateModelCommand updateModelCommand) {
        Brand brand = brandRepository.findById(updateModelCommand.brandId())
                .orElseThrow(() -> new BrandNotFoundException(updateModelCommand.brandId()));

        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(id));

        if (updateModelCommand.name() != null) {
            model = model.changeName(updateModelCommand.name());
        }

        if (updateModelCommand.active() != null) {
            model = model.changeActive(updateModelCommand.active());
        }

        if (updateModelCommand.brandId() != null) {
            model = model.changeBrand(updateModelCommand.brandId());
        }

        Model modelSaved = modelRepository.save(model);

        return modelMapper.toResponse(modelSaved, brand);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!modelRepository.existsById(id)) throw new ModelNotFoundException(id);

        modelRepository.deleteById(id);
    }
}
