package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.brand;

import br.com.teksystem.tekveiculos.vehicle.domain.brand.Brand;
import br.com.teksystem.tekveiculos.vehicle.domain.brand.exception.BrandNotFoundException;
import br.com.teksystem.tekveiculos.vehicle.domain.brand.port.BrandRepository;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.brand.mapper.BrandJpaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class BrandPostgresAdapter implements BrandRepository {
    private final BrandJpaMapper brandJpaMapper;
    private final SpringDataBrandRepository springDataBrandRepository;

    public BrandPostgresAdapter(
            BrandJpaMapper brandJpaMapper,
            SpringDataBrandRepository springDataBrandRepository
    ) {
        this.brandJpaMapper = brandJpaMapper;
        this.springDataBrandRepository = springDataBrandRepository;
    }

    @Override
    public Brand save(Brand brand) {
        BrandJpaEntity brandJpaEntity = brandJpaMapper.toEntity(brand);
        BrandJpaEntity entityJpaSaved = springDataBrandRepository.save(brandJpaEntity);

        return brandJpaMapper.toModel(entityJpaSaved);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return springDataBrandRepository.findById(id).map(brandJpaMapper::toModel);
    }

    @Override
    public List<Brand> findAllById(Set<Long> id) {
        return springDataBrandRepository.findAllById(id)
                .stream()
                .map(brandJpaMapper::toModel)
                .toList();
    }

    @Override
    public List<Brand> findAll() {
        return springDataBrandRepository.findAll()
                .stream()
                .map(brandJpaMapper::toModel)
                .toList();
    }

    @Override
    public boolean existById(Long id) {
        return springDataBrandRepository.existsById(id);
    }

    @Override
    public boolean existByName(String name) {
        return springDataBrandRepository.existsByName(name);
    }

    @Override
    public Brand update(Brand brand) {
        BrandJpaEntity brandJpaEntity = springDataBrandRepository.findById(brand.id())
                .orElseThrow(() -> new BrandNotFoundException(brand.id()));

        brandJpaMapper.updateEntityFromDomain(brand, brandJpaEntity);
        BrandJpaEntity entityJpaSaved = springDataBrandRepository.save(brandJpaEntity);

        return brandJpaMapper.toModel(entityJpaSaved);
    }

    @Override
    public void deleteById(Long id) {
        springDataBrandRepository.deleteById(id);
    }
}
