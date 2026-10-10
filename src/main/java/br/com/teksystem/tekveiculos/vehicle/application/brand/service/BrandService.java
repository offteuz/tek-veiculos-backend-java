package br.com.teksystem.tekveiculos.vehicle.application.brand.service;

import br.com.teksystem.tekveiculos.vehicle.application.brand.dto.input.CreateBrandCommand;
import br.com.teksystem.tekveiculos.vehicle.application.brand.dto.input.UpdateBrandCommand;
import br.com.teksystem.tekveiculos.vehicle.application.brand.dto.output.BrandResponse;
import br.com.teksystem.tekveiculos.vehicle.application.brand.mapper.BrandMapper;
import br.com.teksystem.tekveiculos.vehicle.domain.brand.Brand;
import br.com.teksystem.tekveiculos.vehicle.domain.brand.exception.BrandNotFoundException;
import br.com.teksystem.tekveiculos.vehicle.domain.brand.port.BrandRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public BrandService(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    public BrandResponse save(CreateBrandCommand createBrandCommand) {
        if (brandRepository.existByName(createBrandCommand.name())) {
            throw new IllegalArgumentException("A brand with the same name already exists");
        }

        Brand brand = brandMapper.toDomain(createBrandCommand);
        Brand brandSaved = brandRepository.save(brand);

        return brandMapper.toResponse(brandSaved);
    }

    public List<BrandResponse> findAll() {
        return brandRepository.findAll()
                .stream()
                .map(brandMapper::toResponse)
                .toList();
    }

    public List<BrandResponse> findAllById(Set<Long> ids) {
        return brandRepository.findAllById(ids)
                .stream()
                .map(brandMapper::toResponse)
                .toList();
    }

    public BrandResponse findById(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new BrandNotFoundException(id));

        return brandMapper.toResponse(brand);
    }

    @Transactional
    public BrandResponse update(Long id, UpdateBrandCommand updateBrandCommand) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));

        if (updateBrandCommand.name() != null) {
            brand = brand.changeName(updateBrandCommand.name());
        }

        if (updateBrandCommand.active() != null) {
            brand = brand.changeActive(updateBrandCommand.active());
        }

        Brand brandSaved = brandRepository.save(brand);

        return brandMapper.toResponse(brandSaved);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!brandRepository.existById(id)) throw new BrandNotFoundException(id);

        brandRepository.deleteById(id);
    }
}
