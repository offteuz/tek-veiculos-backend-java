package br.com.teksystem.tekveiculos.vehicle.domain.brand.port;

import br.com.teksystem.tekveiculos.vehicle.domain.brand.Brand;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BrandRepository {
    Brand save(Brand brand);

    List<Brand> findAll();

    List<Brand> findAllById(Set<Long> ids);

    Optional<Brand> findById(Long id);

    boolean existById(Long id);

    boolean existByName(String name);

    Brand update(Brand brand);

    void deleteById(Long id);
}
